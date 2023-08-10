package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.CompraModel;
import com.gabriel.gamestore.api.model.JogoUsuarioModel;
import com.gabriel.gamestore.domain.model.Compra;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CompraAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CompraModel toModel(Compra compra) {
        return modelMapper.map(compra, CompraModel.class);
    }

    public List<CompraModel> toCollectionModel(Set<Compra> compras) {
        return compras.stream()
                .map(this::toModel)
                .toList();
    }


}
