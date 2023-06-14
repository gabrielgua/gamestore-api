package com.gabriel.gamestore.domain.exception;

public class ModoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ModoNaoEncontradoException(Long modoId) {
        super(String.format("Modo de id: #%s não encontrado.", modoId));
    }
}
