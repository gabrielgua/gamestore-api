package com.gabriel.gamestore.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RequisitosSistema {

    private Requisito minimos;
    private Requisito recomendados;
}
