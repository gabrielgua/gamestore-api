package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long categoriaId) {
        return repository.findById(categoriaId).orElseThrow();
    }

    public List<Categoria> buscarVariosPorIds(Set<Long> categoriaIds) {
        return categoriaIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void remover(Long categoriaId) {
        var categoria = buscarPorId(categoriaId);
        repository.delete(categoria);
    }
}
