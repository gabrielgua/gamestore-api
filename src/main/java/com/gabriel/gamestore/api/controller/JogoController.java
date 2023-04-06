package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.service.CategoriaService;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.PlataformaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService jogoService;

    private JogoAssembler assembler;

    @GetMapping
    public List<JogoModel> listar() {
        return assembler.toCollectionModel(jogoService.listar());
    }

    @GetMapping("/{jogoId}")
    public JogoModel buscarPorId(@PathVariable Long jogoId) {
        return assembler.toModel(jogoService.buscarPorId(jogoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogoModel adicionar(@RequestBody JogoRequest request) {
        var jogo = assembler.toEntity(request);
        return assembler.toModel(this.jogoService.salvar(jogo));
    }

    @PutMapping("/{jogoId}")
    public JogoModel editar(@PathVariable Long jogoId, @RequestBody JogoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        assembler.copyToEntity(request, jogo);
        return assembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/{jogoId}")
    public void remover(@PathVariable Long jogoId) {
        jogoService.remover(jogoId);
    }





}
