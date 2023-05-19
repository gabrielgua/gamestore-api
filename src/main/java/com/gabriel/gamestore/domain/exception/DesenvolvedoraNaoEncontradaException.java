package com.gabriel.gamestore.domain.exception;

public class DesenvolvedoraNaoEncontradaException extends EntidadeNaoEncontradaException {

    public DesenvolvedoraNaoEncontradaException(Long desenvolvedoraId) {
        super(String.format("Desenvolvedora de id: #%s não encontrada.", desenvolvedoraId));
    }
}
