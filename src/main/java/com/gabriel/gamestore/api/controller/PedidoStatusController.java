package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos/{codigoPedido}")
public class PedidoStatusController {

    private PedidoService pedidoService;

    @PutMapping("/confirmar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Pedidos.podeConfirmarPedido
    public void confirmarPedido(@PathVariable String codigoPedido) {
        pedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Pedidos.podeCancelarPedido
    public void cancelarPedido(@PathVariable String codigoPedido) {
        pedidoService.cancelar(codigoPedido);
    }

    @PutMapping("/reembolsar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Pedidos.podeReembolsarPedido
    public void reembolsarPedido(@PathVariable String codigoPedido) {
        pedidoService.reembolsar(codigoPedido);
    }

}
