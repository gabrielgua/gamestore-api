package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.RequisitoModel;
import com.gabriel.gamestore.api.model.request.RequisitoRequest;
import com.gabriel.gamestore.domain.model.Requisito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RequisitoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RequisitoModel toModel(Requisito requisito) {
        return modelMapper.map(requisito, RequisitoModel.class);
    }

    public Requisito toEntity(RequisitoRequest request) {
        return modelMapper.map(request, Requisito.class);
    }

    public List<RequisitoModel> toCollectionModel(Set<Requisito> requisitos) {
        return requisitos.stream()
                .map(this::toModel)
                .toList();
    }

    public void copyToEntity(RequisitoRequest request, Requisito requisito) {
        modelMapper.map(request, requisito);
    }
}
