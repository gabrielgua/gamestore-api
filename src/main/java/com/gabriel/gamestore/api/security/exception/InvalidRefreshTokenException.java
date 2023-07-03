package com.gabriel.gamestore.api.security.exception;

import com.gabriel.gamestore.domain.exception.NegocioException;

public class InvalidRefreshTokenException extends NegocioException {

    public InvalidRefreshTokenException() {
        super("Refresh token inválido ou não existente.");
    }
}
