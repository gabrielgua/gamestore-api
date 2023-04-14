package com.gabriel.gamestore.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @Valid
    @NotNull
    private UsuarioIdRequest usuario;

    @Valid
    @NotNull
    private List<JogoIdRequest> jogos;

}
