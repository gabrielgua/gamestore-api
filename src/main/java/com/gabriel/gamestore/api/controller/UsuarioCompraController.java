package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.CompraAssembler;
import com.gabriel.gamestore.api.model.CompraModel;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/compras")
public class UsuarioCompraController {

    private UsuarioService usuarioService;
    private CompraAssembler compraAssembler;

    @GetMapping
    @CheckSecurity.UsuarioJogos.podeListar
    public List<CompraModel> listarCompras(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarPorId(usuarioId);
        return compraAssembler.toCollectionModel(usuario.getCompras());
    }
}
