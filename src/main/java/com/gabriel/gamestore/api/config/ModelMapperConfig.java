package com.gabriel.gamestore.api.config;

import com.gabriel.gamestore.domain.service.JogoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Autowired
    private JogoService service;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
