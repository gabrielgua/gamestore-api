package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.ModoModel;
import com.gabriel.gamestore.api.model.request.ModoRequest;
import com.gabriel.gamestore.domain.model.Modo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ModoModel toModel(Modo modo) {
        return modelMapper.map(modo, ModoModel.class);
    }

    public List<ModoModel> toCollectionModel(List<Modo> modos) {
        return modos.stream()
                .map(this::toModel).toList();
    }

    public Modo toEntity(ModoRequest request) {
        return modelMapper.map(request, Modo.class);
    }

    public void copyToEntity(ModoRequest request, Modo modo) {
        modelMapper.map(request, modo);
    }

}
