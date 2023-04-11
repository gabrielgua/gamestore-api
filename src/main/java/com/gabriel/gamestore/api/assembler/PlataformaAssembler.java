package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.PlataformaModel;
import com.gabriel.gamestore.api.model.request.PlataformaRequest;
import com.gabriel.gamestore.domain.model.Plataforma;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlataformaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PlataformaModel toModel(Plataforma plataforma) {
        return modelMapper.map(plataforma, PlataformaModel.class);
    }

    public List<PlataformaModel> toCollectionModel(List<Plataforma> plataformas) {
        return plataformas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Plataforma toEntity(PlataformaRequest request) {
        return modelMapper.map(request, Plataforma.class);
    }

    public void copyToEntity(PlataformaRequest request, Plataforma plataforma) {
        modelMapper.map(request, plataforma);
    }

}
