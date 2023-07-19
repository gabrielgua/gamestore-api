package com.gabriel.gamestore.api.security.exception;

import com.gabriel.gamestore.domain.exception.NegocioException;

public class InvalidTokenException extends NegocioException {

    public InvalidTokenException() {
        super("Token inválido ou expirado.");
    }
}
