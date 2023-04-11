package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.CategoriaModel;
import com.gabriel.gamestore.api.model.request.CategoriaRequest;
import com.gabriel.gamestore.domain.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaModel toModel(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaModel.class);
    }

    public List<CategoriaModel> toCollectionModel(List<Categoria> categorias) {
        return categorias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Categoria toEntity(CategoriaRequest request) {
        return modelMapper.map(request, Categoria.class);
    }

    public void copyToEntity(CategoriaRequest request, Categoria categoria) {
        modelMapper.map(request, categoria);
    }


}
