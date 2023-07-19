package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.filter.JogoFilter;
import com.gabriel.gamestore.domain.model.PageableResponse;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.PageableResponseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService jogoService;
    private JogoAssembler jogoAssembler;
    private PageableResponseService<JogoResumoModel> pageableService;


    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public PageableResponse<JogoResumoModel> listar(@PageableDefault(size = 5) Pageable pageable, JogoFilter filter) {

        var pageJogos = jogoService.listar(pageable, filter);
        var jogosResumo = jogoAssembler.toPageResumoModel(pageJogos);

        return pageableService.buildResponse(
                "jogos",
                jogosResumo,
                pageable,
                jogoAssembler.toCollectionResumoModel(pageJogos.getContent())
        );
    }

    @GetMapping("/destaques")
    @CheckSecurity.Geral.podeConsultar
    public List<JogoResumoModel> listarJogosEmDestaque() {
        return jogoAssembler.toCollectionResumoModel(jogoService.listarDestaques());
    }

    @GetMapping(params = "id")
    public JogoModel buscarPorId(@RequestParam("id") Long jogoId) {
        return jogoAssembler.toModel(jogoService.buscarPorId(jogoId));
    }

    @GetMapping(params = "uriNome")
    public JogoModel buscarPorUriNome(@RequestParam("uriNome") String uriNome) {
        return jogoAssembler.toModel(jogoService.buscarPorUriNome(uriNome));
    }

    @PostMapping
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel adicionar(@Valid @RequestBody JogoRequest request) {
        var jogo = jogoAssembler.toEntity(request);

        var modoIds = request.getModos();
        var categoriaIds =  request.getCategorias();
        var plataformaIds = request.getPlataformas();

        return jogoAssembler.toModel(jogoService.salvar(jogo, plataformaIds, categoriaIds, modoIds));
    }

    @PutMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public JogoModel editar(@PathVariable Long jogoId, @Valid @RequestBody JogoRequest request) {
        var jogo = jogoService.buscarPorId(jogoId);
        jogoAssembler.copyToEntity(request, jogo);

        var modoIds = request.getModos();
        var categoriaIds =  request.getCategorias();
        var plataformaIds = request.getPlataformas();
        return jogoAssembler.toModel(jogoService.salvar(jogo, plataformaIds, categoriaIds, modoIds));
    }

    @DeleteMapping("/{jogoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long jogoId) {
        jogoService.remover(jogoId);
    }





}
