package com.gabriel.gamestore.domain.model;

import lombok.Getter;

@Getter
public enum StatusPedido {

    CRIADO("Criado", null),
    CONFIRMADO("Confirmado", CRIADO),
    CANCELADO("Cancelado", CRIADO),
    REEMBOLSADO("Reembolsado", CONFIRMADO);

    private final String descricao;
    private final StatusPedido statusAnterior;

    StatusPedido(String descricao, StatusPedido statusAnterior) {
        this.descricao = descricao;
        this.statusAnterior = statusAnterior;
    }

    public boolean podeAlterarPara(StatusPedido novoStatus) {
        return novoStatus.statusAnterior.equals(this);
    }
}
