package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.CompraAssembler;
import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.assembler.PedidoAssembler;
import com.gabriel.gamestore.api.assembler.UsuarioAssembler;
import com.gabriel.gamestore.api.model.CompraModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.model.PedidoModel;
import com.gabriel.gamestore.api.model.UsuarioModel;
import com.gabriel.gamestore.api.security.roleauthotization.AuthorizationConfig;
import com.gabriel.gamestore.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioLogadoController {

    private UsuarioService usuarioService;
    private PedidoAssembler pedidoAssembler;
    private CompraAssembler compraAssembler;
    private UsuarioAssembler usuarioAssembler;
    private AuthorizationConfig authorizationConfig;

    @GetMapping("/meus-dados")
    public UsuarioModel buscarDadosUsuarioLogado() {
        var usuario = usuarioService.buscarPorId(authorizationConfig.getUsuarioId());
        return usuarioAssembler.toModel(usuario);
    }

    @GetMapping("/meus-pedidos")
    public List<PedidoModel> buscarPedidosUsuarioLogado() {
        var usuario = usuarioService.buscarPorId(authorizationConfig.getUsuarioId());
        var pedidos = usuario.getPedidos();
        return pedidoAssembler.toCollectionModel(pedidos.stream().toList());
    }

    @GetMapping("/minhas-compras")
    public List<CompraModel> buscarJogosUsuarioLogado() {
        var usuario = usuarioService.buscarPorId(authorizationConfig.getUsuarioId());
        return compraAssembler.toCollectionModel(usuario.getCompras());
    }





}
