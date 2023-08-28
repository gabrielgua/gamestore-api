package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
    private String username;
    private String avatarUrl;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @CreationTimestamp
    private OffsetDateTime dataCadastro;

    @OneToMany(mappedBy = "usuario")
    private Set<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private Set<Pedido> pedidos;

    @ManyToMany
    @JoinTable(name = "usuario_desejos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "jogo_id"))
    private Set<Jogo> desejos = new HashSet<>();

    public void addCompra(Compra compra) {
        compras.add(compra);
    }
    public void delCompra(Compra compra) {
        compras.remove(compra);
    }

    public void addJogo(Jogo jogo) {
        desejos.add(jogo);
    }

    public void delJogo(Jogo jogo) {
        desejos.remove(jogo);
    }

    public boolean isNovo() {
        return getId() == null;
    }

}
