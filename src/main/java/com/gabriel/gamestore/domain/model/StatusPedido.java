package com.gabriel.gamestore.domain.model;

import lombok.Getter;

@Getter
public enum StatusPedido {

    CRIADO("Criado"),

    CONFIRMADO("Confirmado", CRIADO),
    CANCELADO("Cancelado", CRIADO),

    REEMBOLSADO("Reembolsado", CONFIRMADO);

    private String descricao;
    private StatusPedido statusAnterior;

    StatusPedido(String descricao, StatusPedido... statusAnterior) {}

    public boolean podeAlterarPara(StatusPedido novoStatus) {
        return novoStatus.statusAnterior.equals(this);
    }
}
