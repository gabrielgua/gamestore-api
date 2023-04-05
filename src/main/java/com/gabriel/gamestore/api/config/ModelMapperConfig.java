package com.gabriel.gamestore.api.config;

import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.service.JogoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Set;

@Configuration
public class ModelMapperConfig {

    @Autowired
    private JogoService service;

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(JogoRequest.class, Jogo.class)
                .addMappings(mapper -> mapper.skip(Jogo::setCategorias))
                .addMappings(mapper -> mapper.skip(Jogo::setPlataformas));


        return modelMapper;
    }

}
