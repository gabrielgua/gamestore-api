package com.gabriel.gamestore.domain.filter;

import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Modo;
import com.gabriel.gamestore.domain.model.Plataforma;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class JogoFilterTratado {

    private String nome;
    private Set<Modo> modos;
    private Set<Categoria> categorias;
    private Set<Plataforma> plataformas;
}
