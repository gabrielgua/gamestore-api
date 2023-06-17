package com.gabriel.gamestore.domain.exception;


public class PageNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PageNaoEncontradaException(Integer page) {
        super(String.format("Página #%d não encontrada.", page));
    }
}
