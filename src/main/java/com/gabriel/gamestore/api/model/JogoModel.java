package com.gabriel.gamestore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Desenvolvedora;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.model.Requisito;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class JogoModel {
    private Long id;
    private String nome;
    private String uriNome;

    private String urlVideo;
    private String urlImagem;
    private String urlImgHero;
    private String capsuleImg;
    private OffsetDateTime dataLancamento;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal nota;
    private DesenvolvedoraResumoModel desenvolvedora;
    private Set<RequisitoModel> requisitos;
    private Set<CategoriaModel> categorias;
    private Set<PlataformaModel> plataformas;
    private Set<ModoModel> modos;

}
