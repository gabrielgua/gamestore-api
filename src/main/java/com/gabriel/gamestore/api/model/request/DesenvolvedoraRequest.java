package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesenvolvedoraRequest {

    @NotBlank
    private String nome;
}
