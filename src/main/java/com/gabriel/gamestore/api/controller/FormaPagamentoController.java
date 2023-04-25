package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.FormaPagamentoAssembler;
import com.gabriel.gamestore.api.model.FormaPagamentoModel;
import com.gabriel.gamestore.api.model.request.FormaPagamentoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.model.FormaPagamento;
import com.gabriel.gamestore.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private FormaPagamentoService service;
    private FormaPagamentoAssembler assembler;

    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<FormaPagamentoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{formaPagamentoId}")
    @CheckSecurity.Geral.podeConsultar
    public FormaPagamentoModel buscarPorId(@PathVariable Long formaPagamentoId) {
        return assembler.toModel(service.buscarPorId(formaPagamentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Geral.podeGerenciar
    public FormaPagamentoModel adicionar(@Valid @RequestBody FormaPagamentoRequest request) {
        var formaPagamento = assembler.toEntity(request);
        return assembler.toModel(service.salvar(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    @CheckSecurity.Geral.podeGerenciar
    public FormaPagamentoModel editar(@PathVariable Long formaPagamentoId, @Valid @RequestBody FormaPagamentoRequest request) {
        var formaPagamento = service.buscarPorId(formaPagamentoId);
        assembler.copyToEntity(request, formaPagamento);
        return assembler.toModel(service.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long formaPagamentoId) {
        service.remover(formaPagamentoId);
    }

}
