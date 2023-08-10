package com.gabriel.gamestore.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CompraModel {

    private Long id;
    private Long usuarioId;
    private JogoResumoModel jogo;
    private String codigoPedido;
    private String chaveAtivacao;
    private OffsetDateTime dataCompra;
}
