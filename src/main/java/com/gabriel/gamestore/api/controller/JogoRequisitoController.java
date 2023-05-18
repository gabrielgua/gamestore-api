package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.RequisitoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.RequisitoModel;
import com.gabriel.gamestore.api.model.request.RequisitoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.RequisitoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos/{jogoId}/requisitos")
public class JogoRequisitoController {

    private JogoService jogoService;
    private RequisitoAssembler requisitoAssembler;
    private RequisitoService requisitoService;

    @PostMapping
    @CheckSecurity.Geral.podeGerenciar
    @ResponseStatus(HttpStatus.CREATED)
    public List<RequisitoModel> salvarRequisito(@PathVariable Long jogoId, @Valid @RequestBody List<RequisitoRequest> request) {
        var jogo = jogoService.buscarPorId(jogoId);
        var requisitos = request
                .stream()
                .map(r -> requisitoAssembler.toEntity(r)).toList();

        requisitos.forEach(r -> r.setJogo(jogo));
        return requisitoAssembler.toCollectionModel(requisitoService.salvarVarios(requisitos));
    }

    @PutMapping("/{requisitoId}")
    @CheckSecurity.Geral.podeGerenciar
    public RequisitoModel editarRequisito(@PathVariable Long jogoId, @PathVariable Long requisitoId, @Valid @RequestBody RequisitoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        var requisito = requisitoService.buscarPorId(jogo.getId(), requisitoId);
        requisitoAssembler.copyToEntity(request, requisito);
        return requisitoAssembler.toModel(requisitoService.salvar(requisito));
    }

    @DeleteMapping("/{requisitoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void removerRequisito(@PathVariable Long jogoId, @PathVariable Long requisitoId) {
        var jogo = jogoService.buscarPorId(jogoId);
        var requisito = requisitoService.buscarPorId(jogo.getId(), requisitoId);
        requisitoService.remover(requisito);
    }
}
