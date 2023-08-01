package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
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
    private String uriNome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal nota;
    private OffsetDateTime dataLancamento;
    @ManyToOne
    private Desenvolvedora desenvolvedora;

    private String urlVideo;
    private String urlImagem;
    private String urlImgHero;
    private String capsuleImg;

    @OneToMany(mappedBy = "jogo")
    private Set<Requisito> requisitos;

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

    @ManyToMany
    @JoinTable(name = "jogo_modo",
        joinColumns = @JoinColumn(name = "jogo_id"),
        inverseJoinColumns = @JoinColumn(name = "modo_id"))
    private Set<Modo> modos = new HashSet<>();

    public void addModo(Modo modo) {
        modos.add(modo);
    }
    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void addPlataforma(Plataforma plataforma) {
        plataformas.add(plataforma);
    }


}
