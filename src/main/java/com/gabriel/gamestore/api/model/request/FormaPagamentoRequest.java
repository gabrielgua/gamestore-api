package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoRequest {

    @NotBlank
    private String nome;
}
