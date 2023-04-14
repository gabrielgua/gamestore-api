package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.PedidoAssembler;
import com.gabriel.gamestore.api.model.PedidoResumoModel;
import com.gabriel.gamestore.api.model.request.PedidoRequest;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.service.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService service;
    private PedidoAssembler assembler;

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping(params = "id")
    public PedidoResumoModel buscarPorId(@RequestParam("id") Long pedidoId) {
        return assembler.toResumoModel(service.buscarPorId(pedidoId));
    }

    @GetMapping(params = "codigo")
    public PedidoResumoModel buscarPorCodigo(@RequestParam("codigo") String codigoPedido) {
        return assembler.toResumoModel(service.buscarPorCodigo(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResumoModel fazerPedido(@Valid @RequestBody PedidoRequest request) {
        var pedido = assembler.toEntity(request);
        return assembler.toResumoModel(service.salvar(pedido));
    }



}
