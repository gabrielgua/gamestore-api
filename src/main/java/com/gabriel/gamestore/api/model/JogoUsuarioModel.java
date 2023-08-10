package com.gabriel.gamestore.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogoUsuarioModel {

    private JogoResumoModel jogo;
    private PedidoResumoModel pedido;
    private String chave;
}
