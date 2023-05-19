package com.gabriel.gamestore.api.config.modelmapper;

import com.gabriel.gamestore.api.model.RequisitoModel;
import com.gabriel.gamestore.api.model.request.RequisitoRequest;
import com.gabriel.gamestore.domain.model.Requisito;
import com.gabriel.gamestore.domain.model.TipoRequisito;
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
