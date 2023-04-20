package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.PedidoAssembler;
import com.gabriel.gamestore.api.model.PedidoResumoModel;
import com.gabriel.gamestore.api.model.request.PedidoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.AuthorizationConfig;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.service.PedidoService;
import com.gabriel.gamestore.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;
    private UsuarioService usuarioService;
    private PedidoAssembler assembler;

    @Autowired
    private AuthorizationConfig authConfig;

    @GetMapping
    @CheckSecurity.Pedidos.podeConsultar
    public List<PedidoResumoModel> listar() {
        return assembler.toCollectionModel(pedidoService.listar());
    }

    @GetMapping(params = "id")
    @CheckSecurity.Pedidos.podeConsultar
    public PedidoResumoModel buscarPorId(@RequestParam("id") Long pedidoId) {
        return assembler.toResumoModel(pedidoService.buscarPorId(pedidoId));
    }

    @GetMapping(params = "codigo")
    @CheckSecurity.Pedidos.podeConsultar
    public PedidoResumoModel buscarPorCodigo(@RequestParam("codigo") String codigoPedido) {
        return assembler.toResumoModel(pedidoService.buscarPorCodigo(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Pedidos.podeAdicionar
    public PedidoResumoModel fazerPedido(@Valid @RequestBody PedidoRequest request) {
        var pedido = assembler.toEntity(request);
        var usuario = usuarioService.buscarPorId(authConfig.getUsuarioId());

        pedido.setUsuario(usuario);

        return assembler.toResumoModel(pedidoService.salvar(pedido));
    }



}
