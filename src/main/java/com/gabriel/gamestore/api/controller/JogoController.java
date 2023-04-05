package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.service.JogoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService service;
    private JogoAssembler assembler;

    @GetMapping
    public List<JogoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{jogoId}")
    public JogoModel buscarPorId(@PathVariable Long jogoId) {
        return assembler.toModel(service.buscarPorId(jogoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogoModel adicionar(@RequestBody JogoRequest request) {
        var jogo = assembler.toEntity(request);

        if (request.getCategorias() != null) {
            var categorias = service.adicionarCategorias(request.getCategorias());
            jogo.setCategorias(Set.copyOf(categorias));
        } else {
            jogo.setCategorias(new HashSet<>());
        }

        if (request.getPlataformas() != null) {
            var plataformas = service.adicionarPlataformas(request.getPlataformas());
            jogo.setPlataformas(Set.copyOf(plataformas));
        } else {
            jogo.setPlataformas(new HashSet<>());
        }

        return assembler.toModel(service.salvar(jogo));
    }



}
