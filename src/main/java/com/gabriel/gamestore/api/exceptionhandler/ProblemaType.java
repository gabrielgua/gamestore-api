package com.gabriel.gamestore.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemaType {

    ERRO_GENERICO("/erro-generico", "Erro genérico"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    CORPO_NAO_LEGIVEL("/corpo-nao-legivel", "Corpo não legível"),
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");

    private final String uri;
    private final String title;

    ProblemaType(String path, String title) {
        this.title = title;
        this.uri = "https://gamestore.api/errors" + path;
    }
}
