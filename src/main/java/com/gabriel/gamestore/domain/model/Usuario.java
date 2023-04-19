package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String username;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @CreationTimestamp
    private OffsetDateTime dataCadastro;
    @ManyToMany
    @JoinTable(name = "usuario_jogo",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "jogo_id"))
    private Set<Jogo> jogos;

    public void addJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public void delJogo(Jogo jogo) {
        jogos.remove(jogo);
    }

    public boolean isNovo() {
        return getId() == null;
    }

}
