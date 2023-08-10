package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResumoModel {

    private String codigo;
    private String dataCriacao;
    private String dataConfirmacao;
    private String dataReembolso;
}
