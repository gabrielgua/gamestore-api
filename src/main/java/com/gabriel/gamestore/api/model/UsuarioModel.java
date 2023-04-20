package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
    private String username;
    private String tipo;
    private OffsetDateTime dataCadastro;
    private List<JogoResumoModel> jogos;
}
