package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.PlataformaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlataformaService {

    private PlataformaRepository repository;

    @Transactional(readOnly = true)
    public List<Plataforma> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Plataforma buscarPorId(Long plataformaId) {
        return repository.findById(plataformaId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Plataforma> buscarVariosPorId(Set<Long> plataformaIds) {
        return plataformaIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Plataforma salvar(Plataforma plataforma) {
        return repository.save(plataforma);
    }

    @Transactional
    public void remover(Long plataformaId) {
        var plataforma = buscarPorId(plataformaId);
        repository.delete(plataforma);
    }
}
