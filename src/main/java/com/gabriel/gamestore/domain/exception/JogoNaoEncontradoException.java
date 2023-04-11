package com.gabriel.gamestore.domain.exception;

public class JogoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public JogoNaoEncontradoException(Long jogoId) {
        super(String.format("Jogo de id: #%s não encontrado.", jogoId));
    }
}
