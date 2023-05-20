package com.gabriel.gamestore.api.config.modelmapper;

import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.domain.model.Jogo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
