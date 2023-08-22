package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsernameCheckRequest {

    @NotBlank
    private String username;

    private Long usuarioId;


}
