package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.DesenvolvedoraModel;
import com.gabriel.gamestore.api.model.DesenvolvedoraResumoModel;
import com.gabriel.gamestore.api.model.request.DesenvolvedoraRequest;
import com.gabriel.gamestore.domain.model.Desenvolvedora;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DesenvolvedoraAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public DesenvolvedoraModel toModel(Desenvolvedora desenvolvedora) {
        return modelMapper.map(desenvolvedora, DesenvolvedoraModel.class);
    }

    public DesenvolvedoraResumoModel toResumoModel(Desenvolvedora desenvolvedora) {
        return modelMapper.map(desenvolvedora, DesenvolvedoraResumoModel.class);
    }

    public List<DesenvolvedoraResumoModel> toCollectionModel(List<Desenvolvedora> desenvolvedoras) {
        return desenvolvedoras.stream()
                .map(this::toResumoModel)
                .toList();
    }

    public Desenvolvedora toEntity(DesenvolvedoraRequest request) {
        return modelMapper.map(request, Desenvolvedora.class);
    }

    public void copyToEntity(DesenvolvedoraRequest request, Desenvolvedora desenvolvedora) {
        modelMapper.map(request, desenvolvedora);
    }



}
