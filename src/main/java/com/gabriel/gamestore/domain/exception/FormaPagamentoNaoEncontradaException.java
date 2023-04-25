package com.gabriel.gamestore.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        super(String.format("Forma de pagamento de id: #%s não encontrada.", formaPagamentoId));
    }
}
