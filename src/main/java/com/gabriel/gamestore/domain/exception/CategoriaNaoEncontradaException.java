package com.gabriel.gamestore.domain.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CategoriaNaoEncontradaException(Long categoriaId) {
        super(String.format("Categoria de id: #%s não encontrada.", categoriaId));
    }
}
