package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.UsuarioModel;
import com.gabriel.gamestore.api.model.UsuarioResumoModel;
import com.gabriel.gamestore.api.model.request.UsuarioRequest;
import com.gabriel.gamestore.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public UsuarioResumoModel toResumoModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResumoModel.class);
    }

    public List<UsuarioResumoModel> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toResumoModel)
                .collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioRequest request) {
        return modelMapper.map(request, Usuario.class);
    }

    public void copyToEntity(UsuarioRequest request, Usuario usuario) {
        modelMapper.map(request, usuario);
    }
}
