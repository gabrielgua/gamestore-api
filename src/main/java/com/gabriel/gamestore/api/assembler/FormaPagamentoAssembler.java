package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.FormaPagamentoModel;
import com.gabriel.gamestore.api.model.request.FormaPagamentoRequest;
import com.gabriel.gamestore.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormaPagamentoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasPagamento) {
        return formasPagamento.stream()
                .map(this::toModel)
                .toList();
    }

    public FormaPagamento toEntity(FormaPagamentoRequest request) {
        return modelMapper.map(request, FormaPagamento.class);
    }

    public void copyToEntity(FormaPagamentoRequest request, FormaPagamento formaPagamento) {
        modelMapper.map(request, formaPagamento);
    }
}
