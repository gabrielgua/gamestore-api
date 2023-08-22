package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailCheckRequest {

    @Email
    @NotBlank
    private String email;

    private Long usuarioId;
}
