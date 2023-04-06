package com.gabriel.gamestore.api.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JogoRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal nota;
}
