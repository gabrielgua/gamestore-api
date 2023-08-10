package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
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

    public void addCompra(Compra compra) {
        compras.add(compra);
    }

    public void delCompra(Compra compra) {
        compras.remove(compra);
    }

    public boolean isNovo() {
        return getId() == null;
    }

}
