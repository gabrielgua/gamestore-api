package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.UsuarioAssembler;
import com.gabriel.gamestore.api.model.UsuarioModel;
import com.gabriel.gamestore.api.model.UsuarioResumoModel;
import com.gabriel.gamestore.api.model.request.UsuarioComSenhaRequest;
import com.gabriel.gamestore.api.model.request.UsuarioRequest;
import com.gabriel.gamestore.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService service;
    private UsuarioAssembler assembler;

    @GetMapping
    public List<UsuarioResumoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscarPorId(@PathVariable Long usuarioId) {
        return assembler.toModel(service.buscarPorId(usuarioId));
    }

    @PostMapping
    public UsuarioModel salvar(@Valid @RequestBody UsuarioComSenhaRequest request) {
        var usuario = assembler.toEntity(request);
        return assembler.toModel(service.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel editar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioRequest request) {
        var usuario = service.buscarPorId(usuarioId);
        assembler.copyToEntity(request, usuario);
        return assembler.toModel(service.salvar(usuario));
    }

    @DeleteMapping("/{usuarioId}")
    public void remover(@PathVariable Long usuarioId) {
        service.remover(usuarioId);
    }
}
