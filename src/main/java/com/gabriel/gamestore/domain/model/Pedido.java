package com.gabriel.gamestore.domain.model;

import com.gabriel.gamestore.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private BigDecimal valorTotal;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataReembolso;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "pedido_jogo",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "jogo_id"))
    private Set<Jogo> jogos = new HashSet<>();

    public void confirmarPedido() {
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }

    public void cancelarPedido() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    public void reembolsarPedido() {
        setStatus(StatusPedido.REEMBOLSADO);
        setDataReembolso(OffsetDateTime.now());
    }

    private void setStatus(StatusPedido status) {
        if (!getStatus().podeAlterarPara(status)) {
            throw new NegocioException(
                    String.format("Status do pedido '%s', não pode ser alterado de '%s' para '%s'.",
                            getCodigo(), getStatus().getDescricao(), status.getDescricao()));
        }

        this.status = status;
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

    public void calcularValorTotal() {
        var valorTotal = getJogos().stream()
                .map(Jogo::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        setValorTotal(valorTotal);
    }



}
