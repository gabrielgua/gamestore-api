package com.gabriel.gamestore.api.model.request;

import com.gabriel.gamestore.domain.model.TipoRequisito;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoRequest {

    @NotNull
    private TipoRequisito tipo;
    private String sistema;
    private String memoria;
    private String processador;
    private String placaDeVideo;
    private String armazenamento;
}
