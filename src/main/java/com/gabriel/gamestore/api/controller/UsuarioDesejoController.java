package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.JogoService;
import com.gabriel.gamestore.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/desejos")
public class UsuarioDesejoController {

    private final UsuarioService usuarioService;
    private final JogoService jogoService;
    private final JogoAssembler jogoAssembler;


    @GetMapping
    @CheckSecurity.Usuarios.podeGerenciar
    public List<JogoResumoModel> listarDesejos(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        return jogoAssembler.toCollectionResumoModel(usuario.getDesejos().stream().toList());
    }

    @PutMapping("/{jogoId}")
    @CheckSecurity.Usuarios.podeGerenciar
    public void addJogo(@PathVariable Long usuarioId, @PathVariable Long jogoId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        var jogo = jogoService.buscarPorId(jogoId);

        usuarioService.adicionarJogoDesejo(usuario, jogo);
    }

    @DeleteMapping("/{jogoId}")
    @CheckSecurity.Usuarios.isUsuarioAutenticado
    public void removeJogo(@PathVariable Long usuarioId, @PathVariable Long jogoId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        var jogo = jogoService.buscarPorId(jogoId);

        usuarioService.removerJogoDesejo(usuario, jogo);
    }
}
