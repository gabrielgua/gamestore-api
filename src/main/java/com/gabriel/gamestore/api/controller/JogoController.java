package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.assembler.RequisitoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.model.Requisito;
import com.gabriel.gamestore.domain.repository.RequisitoRepository;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.RequisitoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService jogoService;
    private RequisitoService requisitoService;

    private JogoAssembler jogoAssembler;
    private RequisitoAssembler requisitoAssembler;

    private RequisitoRepository requisitoRepository;

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
        var requisitos = request.getRequisitos().stream()
                        .map(r -> requisitoAssembler.toEntity(r))
                        .collect(Collectors.toList());


        jogoService.salvar(jogo);
        requisitos.forEach(requisito -> requisito.setJogo(jogo));
        requisitoRepository.saveAll(requisitos);

        jogo.setRequisitos(Set.copyOf(requisitos));

        return jogoAssembler.toModel(jogo);
    }

    @PutMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel editar(@PathVariable Long jogoId, @Valid @RequestBody JogoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        jogoAssembler.copyToEntity(request, jogo);
        return jogoAssembler.toModel(jogoService.salvar(jogo));
    }

    @DeleteMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long jogoId) {
        jogoService.remover(jogoId);
    }





}
