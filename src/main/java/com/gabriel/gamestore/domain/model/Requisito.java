package com.gabriel.gamestore.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Requisito {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Jogo jogo;
    @Enumerated(EnumType.STRING)
    private TipoRequisito tipo;
    private String sistema;
    private String memoria;
    private String processador;
    private String placaDeVideo;
    private String armazenamento;
}
