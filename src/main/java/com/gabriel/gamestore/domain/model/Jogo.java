package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jogo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal nota;
    @ManyToMany
    @JoinTable(name = "jogo_categoria",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "jogo_plataforma",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id"))
    private Set<Plataforma> plataformas = new HashSet<>();

    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void remCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }

    public void addPlataforma(Plataforma plataforma) {
        plataformas.add(plataforma);
    }

    public void remPlataforma(Plataforma plataforma) {
        plataformas.remove(plataforma);
    }
}
