package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.PedidoResumoModel;
import com.gabriel.gamestore.api.model.request.PedidoRequest;
import com.gabriel.gamestore.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModel toResumoModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoModel.class);
    }

    public Pedido toEntity(PedidoRequest request) {
        return modelMapper.map(request, Pedido.class);
    }

}
