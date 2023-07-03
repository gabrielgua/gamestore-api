package com.gabriel.gamestore.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public UsuarioNaoEncontradoException(Long usuarioId) {
        super(String.format("Usuário de id: #%s não encontrado.", usuarioId));
    }

    public UsuarioNaoEncontradoException(String term) {
        super(String.format("Usuário não encontrado para email ou username '%s'", term));
    }
}
