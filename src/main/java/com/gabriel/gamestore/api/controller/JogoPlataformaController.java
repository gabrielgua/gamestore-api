package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.PlataformaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos/{jogoId}/plataformas")
public class JogoPlataformaController {

    private JogoAssembler assembler;
    private JogoService jogoService;
    private PlataformaService plataformaService;

    @GetMapping
    public List<Plataforma> listarPlataformas(@PathVariable Long jogoId) {
        var jogo = jogoService.buscarPorId(jogoId);
        return jogo.getPlataformas().stream().toList();
    }

    @PutMapping
    public JogoModel editarPlataformas(@PathVariable Long jogoId, @RequestBody Set<Long> plataformasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var plataformas = plataformaService.buscarVariosPorId(plataformasIds);

        jogoService.editarPlataformas(jogo, plataformas);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @PutMapping("/adicionar")
    public JogoModel adicionarPlataformas(@PathVariable Long jogoId, @RequestBody Set<Long> plataformasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var plataformas = plataformaService.buscarVariosPorId(plataformasIds);

        jogoService.adicionarPlataformas(jogo, plataformas);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/remover")
    public JogoModel removerPlataformas(@PathVariable Long jogoId, @RequestBody Set<Long> plataformasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var plataformas = plataformaService.buscarVariosPorId(plataformasIds);

        jogoService.removerPlataformas(jogo, plataformas);
        return assembler.toModel(jogoService.salvar(jogo));
    }




}

