package com.gabriel.gamestore.domain.exception;

public class PlataformaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PlataformaNaoEncontradaException(Long plataformaId) {
        super(String.format("Plataforma de id: #%s não encontrada.", plataformaId));
    }
}
