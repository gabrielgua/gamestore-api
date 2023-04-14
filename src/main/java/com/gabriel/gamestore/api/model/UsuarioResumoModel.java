package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResumoModel {

    private Long id;
    private String nome;
    private String email;
    private String username;
    private OffsetDateTime dataCadastro;
}
