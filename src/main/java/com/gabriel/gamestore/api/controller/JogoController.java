package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.CategoriaService;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.PlataformaService;
import com.gabriel.gamestore.domain.service.RequisitoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService jogoService;
    private JogoAssembler jogoAssembler;
    private RequisitoService requisitoService;

    private CategoriaService categoriaService;

    private PlataformaService plataformaService;


    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<JogoResumoModel> listar() {
        return jogoAssembler.toCollectionModel(jogoService.listar());
    }

    @GetMapping(params = "id")
    public JogoModel buscarPorId(@RequestParam("id") Long jogoId) {
        return jogoAssembler.toModel(jogoService.buscarPorId(jogoId));
    }

    @GetMapping(params = "nome")
    public JogoModel buscarPorUriNome(@RequestParam("nome") String uriNome) {
        return jogoAssembler.toModel(jogoService.buscarPorUriNome(uriNome));
    }

    @PostMapping
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel adicionar(@Valid @RequestBody JogoRequest request) {
        var jogo = jogoAssembler.toEntity(request);
        var categorias = categoriaService.buscarVariosPorIds(request.getCategorias());
        var plataformas = plataformaService.buscarVariosPorId(request.getPlataformas());

        jogoService.editarCategorias(jogo, categorias);
        jogoService.editarPlataformas(jogo, plataformas);

        return jogoAssembler.toModel(jogoService.salvar(jogo));
    }

    @PutMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel editar(@PathVariable Long jogoId, @Valid @RequestBody JogoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        jogoAssembler.copyToEntity(request, jogo);

        var categorias = categoriaService.buscarVariosPorIds(request.getCategorias());
        var plataformas = plataformaService.buscarVariosPorId(request.getPlataformas());

        jogoService.editarCategorias(jogo, categorias);
        jogoService.editarPlataformas(jogo, plataformas);

        return jogoAssembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long jogoId) {
        jogoService.remover(jogoId);
    }





}
