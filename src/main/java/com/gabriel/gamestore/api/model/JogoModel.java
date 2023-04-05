package com.gabriel.gamestore.api.model;

import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Plataforma;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class JogoModel {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal nota;
    private Set<Categoria> categorias;
    private Set<Plataforma> plataformas;
}
