package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

    public Compra(Jogo jogo, Usuario usuario, Pedido pedido, String chaveAtivacao) {
        setJogo(jogo);
        setUsuario(usuario);
        setDataCompra(pedido.getDataConfirmacao());
        setCodigoPedido(pedido.getCodigo());
        setChaveAtivacao(chaveAtivacao);
    }

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Jogo jogo;

    private String codigoPedido;
    private OffsetDateTime dataCompra;
    private String chaveAtivacao;
}
