package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.PlataformaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlataformaService {

    private PlataformaRepository repository;

    @Transactional(readOnly = true)
    public Plataforma buscarPorId(Long plataformaId) {
        return repository.findById(plataformaId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Plataforma> buscarVariosPorId(Set<Long> plataformaIds) {
        return repository.findAllById(plataformaIds);
    }
}
