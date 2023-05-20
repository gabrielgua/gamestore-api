package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.DesenvolvedoraAssembler;
import com.gabriel.gamestore.api.model.DesenvolvedoraModel;
import com.gabriel.gamestore.api.model.DesenvolvedoraResumoModel;
import com.gabriel.gamestore.api.model.request.DesenvolvedoraRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.DesenvolvedoraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/desenvolvedoras")
public class DesenvolvedoraController {

    private DesenvolvedoraService desenvolvedoraService;
    private DesenvolvedoraAssembler desenvolvedoraAssembler;

    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<DesenvolvedoraResumoModel> listar() {
        return desenvolvedoraAssembler.toCollectionModel(desenvolvedoraService.listar());
    }

    @GetMapping("/{desenvolvedoraId}")
    @CheckSecurity.Geral.podeConsultar
    public DesenvolvedoraModel buscarPorId(@PathVariable Long desenvolvedoraId) {
        var desenvolvedora = desenvolvedoraService.buscarPorId(desenvolvedoraId);
        return desenvolvedoraAssembler.toModel(desenvolvedora);
    }

    @PostMapping
    @CheckSecurity.Geral.podeGerenciar
    @ResponseStatus(HttpStatus.CREATED)
    public DesenvolvedoraResumoModel salvar(@Valid @RequestBody DesenvolvedoraRequest request) {
        var desenvolvedora = desenvolvedoraAssembler.toEntity(request);
        return desenvolvedoraAssembler.toResumoModel(desenvolvedoraService.salvar(desenvolvedora));
    }

    @PutMapping("/{desenvolvedoraId}")
    @CheckSecurity.Geral.podeGerenciar
    public DesenvolvedoraResumoModel editar(@PathVariable Long desenvolvedoraId, @Valid @RequestBody DesenvolvedoraRequest request) {
        var desenvolvedora = desenvolvedoraService.buscarPorId(desenvolvedoraId);
        desenvolvedoraAssembler.copyToEntity(request, desenvolvedora);
        return desenvolvedoraAssembler.toResumoModel(desenvolvedoraService.salvar(desenvolvedora));
    }

    @DeleteMapping("/{desenvolvedoraId}")
    @CheckSecurity.Geral.podeGerenciar
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long desenvolvedoraId) {
        var desenvolvedora = desenvolvedoraService.buscarPorId(desenvolvedoraId);
        desenvolvedoraService.remover(desenvolvedora);
    }




}
