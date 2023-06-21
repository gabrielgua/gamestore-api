package com.gabriel.gamestore.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JogoFilter {

    private String nome;
    private Set<Long> modosIds;
    private Set<Long> categoriasIds;
    private Set<Long> plataformasIds;
}
