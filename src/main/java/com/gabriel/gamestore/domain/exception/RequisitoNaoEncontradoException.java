package com.gabriel.gamestore.domain.exception;

public class RequisitoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RequisitoNaoEncontradoException(Long jogoId, Long requisitoId) {
        super(String.format("Requisito de id: #%s não encontrado para o jogo de id: #%s.", requisitoId, jogoId));
    }
}
