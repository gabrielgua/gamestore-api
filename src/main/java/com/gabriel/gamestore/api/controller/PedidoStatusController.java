package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos/{codigoPedido}")
public class PedidoStatusController {

    private PedidoService service;

    @PutMapping("/confirmar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable String codigoPedido) {
        service.confirmar(codigoPedido);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable String codigoPedido) {
        service.cancelar(codigoPedido);
    }

    @PutMapping("/reembolsar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reembolsarPedido(@PathVariable String codigoPedido) {
        service.reembolsar(codigoPedido);
    }

}
