package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.service.CategoriaService;
import com.gabriel.gamestore.domain.service.JogoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos/{jogoId}/categorias")
public class JogoCategoriaController {

    private JogoAssembler assembler;
    private JogoService jogoService;
    private CategoriaService categoriaService;


    @GetMapping
    public List<Categoria> listarCategorias(@PathVariable Long jogoId) {
        var jogo = jogoService.buscarPorId(jogoId);
        return jogo.getCategorias().stream().toList();
    }

    @PutMapping
    public JogoModel editarCategorias(@PathVariable Long jogoId, @RequestBody Set<Long> categoriasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var categorias = categoriaService.buscarVariosPorIds(categoriasIds);

        jogoService.editarCategorias(jogo, categorias);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @PutMapping("/adicionar")
    public JogoModel adicionarCategorias(@PathVariable Long jogoId, @RequestBody Set<Long> categoriasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var categorias = categoriaService.buscarVariosPorIds(categoriasIds);

        jogoService.adicionarCategorias(jogo, categorias);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/remover")
    public JogoModel removerCategorias(@PathVariable Long jogoId, @RequestBody Set<Long> categoriasIds) {
        var jogo = jogoService.buscarPorId(jogoId);
        var categorias = categoriaService.buscarVariosPorIds(categoriasIds);

        jogoService.removerCategorias(jogo, categorias);
        return assembler.toModel(jogoService.salvar(jogo));
    }
}
