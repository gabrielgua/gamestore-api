package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.JogoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService jogoService;

    private JogoAssembler assembler;

    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<JogoResumoModel> listar() {
        return assembler.toCollectionModel(jogoService.listar());
    }

    @GetMapping(params = "id")
    public JogoModel buscarPorId(@RequestParam("id") Long jogoId) {
        return assembler.toModel(jogoService.buscarPorId(jogoId));
    }

    @GetMapping(params = "nome")
    public JogoModel buscarPorUriNome(@RequestParam("nome") String uriNome) {
        return assembler.toModel(jogoService.buscarPorUriNome(uriNome));
    }

    @PostMapping
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel adicionar(@Valid @RequestBody JogoRequest request) {
        var jogo = assembler.toEntity(request);
        return assembler.toModel(this.jogoService.salvar(jogo));
    }

    @PutMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel editar(@PathVariable Long jogoId, @Valid @RequestBody JogoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        assembler.copyToEntity(request, jogo);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long jogoId) {
        jogoService.remover(jogoId);
    }





}
