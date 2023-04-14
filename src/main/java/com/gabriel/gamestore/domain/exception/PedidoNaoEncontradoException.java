package com.gabriel.gamestore.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(Long pedidoId) {
        super(String.format("Pedido de id: #%s não encontrado.", pedidoId));
    }

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Jogo de código: '%s' não encontrado.", codigoPedido));
    }
}
