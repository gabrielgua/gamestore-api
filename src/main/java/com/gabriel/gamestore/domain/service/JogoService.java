package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class JogoService {

    private JogoRepository repository;
    private CategoriaService categoriaService;
    private PlataformaService plataformaService;

    @Transactional(readOnly = true)
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Jogo buscarPorId(Long jogoId) {
        return repository.findById(jogoId).orElseThrow();
    }

    @Transactional
    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public List<Categoria> adicionarCategorias(Set<Long> categoriaIds) {
        return categoriaService.buscarVariosPorIds(categoriaIds);
    }

    public void removerCategorias(Jogo jogo, Set<Long> categoriaIds) {
        categoriaIds.forEach(categoriaId -> {
            var categoria = categoriaService.buscarPorId(categoriaId);
        });
    }

    public List<Plataforma> adicionarPlataformas(Set<Long> plataformaIds) {
        return plataformaService.buscarVariosPorId(plataformaIds);
    }

    public void removerPlataformas(Jogo jogo, Set<Long> plataformaIds) {
        plataformaIds.forEach(plataformaId -> {
            var plataforma = plataformaService.buscarPorId(plataformaId);
        });
    }



}
