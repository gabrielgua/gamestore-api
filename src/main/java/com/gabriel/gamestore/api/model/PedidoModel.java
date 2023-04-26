package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoModel {

    private Long id;
    private String codigo;
    private BigDecimal valorTotal;
    private FormaPagamentoModel formaPagamento;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataReembolso;
    private String status;
    private UsuarioPedidoModel usuario;
    private List<JogoPedidoModel> jogos;

}
