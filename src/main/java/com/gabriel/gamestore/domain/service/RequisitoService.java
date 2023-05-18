package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Requisito;
import com.gabriel.gamestore.domain.repository.RequisitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RequisitoService {

    private RequisitoRepository repository;

    @Transactional
    public Requisito salvar(Requisito requisito) {
        return repository.save(requisito);
    }
}
