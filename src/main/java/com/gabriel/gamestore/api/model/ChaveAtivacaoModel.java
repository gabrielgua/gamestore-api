package com.gabriel.gamestore.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChaveAtivacaoModel {

    private Long id;
    private String chave;
    private Long jogoId;
}
