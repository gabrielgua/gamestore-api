package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.PedidoAssembler;
import com.gabriel.gamestore.api.model.PedidoModel;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/pedidos")
public class UsuarioPedidoController {

    private UsuarioService service;
    private PedidoAssembler pedidoAssembler;

    @GetMapping
    @CheckSecurity.Pedidos.podeConsultarPropriosPedidos
    public List<PedidoModel> listar(@PathVariable Long usuarioId) {
        var usuario = service.buscarPorId(usuarioId);
        return pedidoAssembler.toCollectionModel(usuario.getPedidos().stream().toList());
    }
}
