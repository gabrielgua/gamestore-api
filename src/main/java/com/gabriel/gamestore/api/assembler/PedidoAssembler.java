package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.PedidoModel;
import com.gabriel.gamestore.api.model.request.PedidoRequest;
import com.gabriel.gamestore.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toResumoModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public Pedido toEntity(PedidoRequest request) {
        return modelMapper.map(request, Pedido.class);
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toResumoModel)
                .toList();
    }

}
