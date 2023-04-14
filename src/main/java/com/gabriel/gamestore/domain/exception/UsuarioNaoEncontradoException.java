package com.gabriel.gamestore.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public UsuarioNaoEncontradoException(Long usuarioId) {
        super(String.format("Usuário de id: #%s não encontrado.", usuarioId));
    }
}
