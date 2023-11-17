package com.gabriel.gamestore.api.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import com.gabriel.gamestore.api.assembler.UsuarioAssembler;
import com.gabriel.gamestore.api.model.UsuarioModel;
import com.gabriel.gamestore.api.model.request.*;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService service;
    private UsuarioAssembler assembler;

    @GetMapping
    @CheckSecurity.Usuarios.podeConsultar
    public List<UsuarioModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @PostMapping("/check-username")
    @CheckSecurity.Geral.podeConsultar
    public boolean usernameTaken(@RequestBody @Valid UsernameCheckRequest request) {
        if (request.getUsuarioId() != null) {
            var usuario = service.buscarPorId(request.getUsuarioId());
            if (usuario.getUsername().equals(request.getUsername())) {
                return false;
            }
        }

        return service.usernameTaken(request.getUsername());
    }

    @PostMapping("/check-email")
    @CheckSecurity.Geral.podeConsultar
    public boolean emailTaken(@RequestBody @Valid EmailCheckRequest request) {
        if (request.getUsuarioId() != null) {
            var usuario = service.buscarPorId(request.getUsuarioId());
            if (usuario.getEmail().equals(request.getEmail())) {
                return false;
            }
        }

        return service.emailTaken(request.getEmail());
    }

    @GetMapping("/{usuarioId}")
    @CheckSecurity.Usuarios.podeBuscar
    public UsuarioModel buscarPorId(@PathVariable Long usuarioId) {
        return assembler.toModel(service.buscarPorId(usuarioId));
    }

    @PostMapping
    @CheckSecurity.Usuarios.podeConsultar
    public UsuarioModel salvar(@Valid @RequestBody UsuarioComSenhaRequest request) {
        var usuario = assembler.toEntity(request);
        return assembler.toModel(service.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    @CheckSecurity.Usuarios.podeGerenciar
    public UsuarioModel editar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioRequest request) {
        var usuario = service.buscarPorId(usuarioId);
        assembler.copyToEntity(request, usuario);
        return assembler.toModel(service.salvar(usuario));
    }

    @DeleteMapping("/{usuarioId}")
    @CheckSecurity.Usuarios.podeGerenciar
    public void remover(@PathVariable Long usuarioId) {
        service.remover(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @CheckSecurity.Usuarios.podeAlterarPropriaSenha
    public void alterarSenha(@PathVariable Long usuarioId, @Valid @RequestBody SenhaRequest request) {
        service.alterarSenha(usuarioId, request.getSenhaAtual(), request.getSenhaNova());
    }

    @PutMapping("/{usuarioId}/admin")
    @CheckSecurity.Usuarios.podeGerenciarPermissoesAdmin
    public void concederAdmin(@PathVariable Long usuarioId) {
        var usuario = service.buscarPorId(usuarioId);
        service.tornarAdmin(usuario);
    }

    @DeleteMapping("/{usuarioId}/admin")
    @CheckSecurity.Usuarios.podeGerenciarPermissoesAdmin
    public void revogarAdmin(@PathVariable Long usuarioId) {
        var usuario = service.buscarPorId(usuarioId);
        service.revogarAdmin(usuario);
    }

}
