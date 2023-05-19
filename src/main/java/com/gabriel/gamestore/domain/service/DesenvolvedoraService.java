package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.DesenvolvedoraNaoEncontradaException;
import com.gabriel.gamestore.domain.model.Desenvolvedora;
import com.gabriel.gamestore.domain.repository.DesenvolvedoraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DesenvolvedoraService {

    private DesenvolvedoraRepository repository;

    @Transactional(readOnly = true)
    public Desenvolvedora buscarPorId(Long desenvolvedoraId) {
        return repository.findById(desenvolvedoraId).orElseThrow(() -> new DesenvolvedoraNaoEncontradaException(desenvolvedoraId));
    }
}

