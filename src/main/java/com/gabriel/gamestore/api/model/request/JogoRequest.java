package com.gabriel.gamestore.api.model.request;

import com.gabriel.gamestore.domain.model.Requisito;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class JogoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private BigDecimal nota;

    @NotBlank
    private String urlVideo;

    @NotBlank
    private String urlImagem;

    @NotNull
    private OffsetDateTime dataLancamento;

    @NotNull
    private Set<Long> categorias;
    @NotNull
    private Set<Long> plataformas;
    @Valid
    @NotNull
    private DesenvolvedoraIdRequest desenvolvedora;
}
