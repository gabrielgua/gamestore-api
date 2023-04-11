package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.CategoriaNaoEncontradaException;
import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long categoriaId) {
        return repository.findById(categoriaId).orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
    }

    @Transactional(readOnly = true)
    public List<Categoria> buscarVariosPorIds(Set<Long> categoriaIds) {
        return categoriaIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public void remover(Long categoriaId) {
        try {
            var categoria = buscarPorId(categoriaId);
            repository.delete(categoria);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format("Categoria de id: #%s está em uso. Remova-a de qualquer jogo e tente novamente.", categoriaId));
        }
    }
}
