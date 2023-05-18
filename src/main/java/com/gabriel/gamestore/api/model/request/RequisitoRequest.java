package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoRequest {
    @NotBlank
    private String descricao;
    private String sistema;
    private String memoria;
    private String processador;
    private String placaDeVideo;

    private String armazenamento;
}
