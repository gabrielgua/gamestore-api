package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/jogos")
public class UsuarioJogoController {

    private UsuarioService usuarioService;
    private JogoService jogoService;
    private JogoAssembler jogoAssembler;

    @GetMapping
    @CheckSecurity.UsuarioJogos.podeListar
    public List<JogoResumoModel> listarJogos(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        return jogoAssembler.toCollectionModel(usuario.getJogos().stream().toList());
    }

    @PutMapping
    @CheckSecurity.UsuarioJogos.podeGerenciar
    public List<JogoResumoModel> adicionarJogos(@PathVariable Long usuarioId, @RequestBody List<Long> jogosId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        var jogos = jogoService.buscarVariosPorId(jogosId);

        usuarioService.adicionarJogos(usuario, jogos);
        usuarioService.salvar(usuario);
        return jogoAssembler.toCollectionModel(usuario.getJogos().stream().toList());
    }

    @DeleteMapping
    @CheckSecurity.UsuarioJogos.podeGerenciar
    public List<JogoResumoModel> removerJogos(@PathVariable Long usuarioId, @RequestBody List<Long> jogosId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        var jogos = jogoService.buscarVariosPorId(jogosId);

        usuarioService.removerJogos(usuario, jogos);
        usuarioService.salvar(usuario);
        return jogoAssembler.toCollectionModel(usuario.getJogos().stream().toList());
    }


}
