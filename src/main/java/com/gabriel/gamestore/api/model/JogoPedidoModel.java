package com.gabriel.gamestore.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JogoPedidoModel {

    private Long id;
    private String nome;
    private String urlImagem;
    private BigDecimal preco;

}
