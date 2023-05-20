package com.gabriel.gamestore.api.model;

import com.gabriel.gamestore.domain.model.Jogo;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DesenvolvedoraModel {

    private Long id;
    private String nome;

    private Set<JogoResumoModel> jogos;
}
