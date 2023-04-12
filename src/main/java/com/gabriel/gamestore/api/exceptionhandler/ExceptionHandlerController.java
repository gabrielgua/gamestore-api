package com.gabriel.gamestore.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.ref.Reference;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final String MSG_ERRO_GENERICO = "Erro interno no sistema. Tente novamente mais tarde.";
    private static final String MSG_DADOS_INVALIDOS = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

    @Autowired
    private MessageSource messageSource;

    private Problema.ProblemaBuilder createProblemaBuilder(HttpStatus status, ProblemaType type, String detail) {
        return Problema.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .status(statusCode.value())
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .userMessage(MSG_ERRO_GENERICO)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        if (body instanceof String) {
            body = Problema.builder()
                    .status(statusCode.value())
                    .title((String) body)
                    .userMessage(MSG_ERRO_GENERICO)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private List<Problema.Objeto> fieldErrorsToList(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(error -> {
                    String name = error.getObjectName();
                    String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

                    if (error instanceof FieldError) {
                        name = ((FieldError) error).getField();
                    }

                    return Problema.Objeto.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var status = HttpStatus.valueOf(statusCode.value());
        var detail = MSG_DADOS_INVALIDOS;
        var type = ProblemaType.DADOS_INVALIDOS;
        List<Problema.Objeto> problemObjects = fieldErrorsToList(bindingResult);

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(detail)
                .fields(problemObjects)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, statusCode, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, statusCode, request);
        }

        var status = HttpStatus.valueOf(statusCode.value());
        var detail = "Corpo da requisição está inválido. Verifique o erro da sintaxe.";
        var type = ProblemaType.CORPO_NAO_LEGIVEL;

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(MSG_ERRO_GENERICO)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var status = HttpStatus.valueOf(statusCode.value());
        var path = joinPath(ex.getPath());
        var className = ex.getTargetType().getSimpleName();
        var type = ProblemaType.CORPO_NAO_LEGIVEL;
        var detail = String.format("A propriedade '%s' recebeu o valor '%s', que é inválido. Informe um valor compatível com '%s'.",
                path, ex.getValue(), className);

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(MSG_ERRO_GENERICO)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var status = HttpStatus.valueOf(statusCode.value());
        var path = joinPath(ex.getPath());
        var className = ex.getReferringClass().getSimpleName();
        var type = ProblemaType.CORPO_NAO_LEGIVEL;
        var detail = String.format("A propriedade '%s' não existe para '%s'. Corrija ou remova essa propriedade e tente novamente.", path, className);

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(MSG_ERRO_GENERICO)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var type = ProblemaType.ERRO_GENERICO;
        var detail = MSG_ERRO_GENERICO;

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var type = ProblemaType.ERRO_GENERICO;
        var detail = ex.getMessage();

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(MSG_ERRO_GENERICO)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var type = ProblemaType.ENTIDADE_NAO_ENCONTRADA;

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(detail)
                .build();


        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
        var status = HttpStatus.CONFLICT;
        var detail = ex.getMessage();
        var type = ProblemaType.ENTIDADE_EM_USO;

        var problema = createProblemaBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
