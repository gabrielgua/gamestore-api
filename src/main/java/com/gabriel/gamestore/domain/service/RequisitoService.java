package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.RequisitoNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Requisito;
import com.gabriel.gamestore.domain.repository.RequisitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequisitoService {

    private RequisitoRepository repository;
    @Transactional(readOnly = true)
    public Requisito buscarPorId(Long jogoId, Long requisitoId) {
        return repository.findById(jogoId, requisitoId).orElseThrow(() -> new RequisitoNaoEncontradoException(jogoId, requisitoId));
    }

    @Transactional
    public Requisito salvar(Requisito requisito) {
        return repository.save(requisito);
    }

    @Transactional
    public void remover(Requisito requisito) {
        repository.delete(requisito);
    }

    public Set<Requisito> salvarVarios(List<Requisito> requisitos) {
        return requisitos.stream()
                .map(this::salvar)
                .collect(Collectors.toSet());
    }
}
