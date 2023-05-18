package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabriel.gamestore.domain.model.TipoRequisito;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequisitoModel {

    private Long id;
    private TipoRequisito tipo;
    private String sistema;
    private String memoria;
    private String processador;
    private String placaDeVideo;
    private String armazenamento;
}
