package com.gabriel.gamestore.api.model.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @Nullable
    private String nome;
}
