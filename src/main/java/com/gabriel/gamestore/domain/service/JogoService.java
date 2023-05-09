package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.exception.JogoNaoEncontradoException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JogoService {

    private JogoRepository repository;


    @Transactional(readOnly = true)
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Jogo buscarPorId(Long jogoId) {
        return repository.findById(jogoId).orElseThrow(() -> new JogoNaoEncontradoException(jogoId));
    }

    @Transactional
    public Jogo buscarPorUriNome(String uriNome) {
        return repository.findByUriNome(uriNome).orElseThrow(() -> new JogoNaoEncontradoException(uriNome));
    }

    @Transactional(readOnly = true)
    public List<Jogo> buscarVariosPorId(List<Long> jogosIds) {
        return jogosIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Jogo salvar(Jogo jogo) {
        var uriNome = transformarNomeToUriNome(jogo.getNome());
        verificarUriNomeJaCadastrado(uriNome);
        jogo.setUriNome(uriNome);
        return repository.save(jogo);
    }


    @Transactional
    public void editarCategorias(Jogo jogo, List<Categoria> categorias) {
        jogo.setCategorias(new HashSet<>());
        adicionarCategorias(jogo, categorias);
    }

    @Transactional
    public void editarPlataformas(Jogo jogo, List<Plataforma> plataformas) {
        jogo.setPlataformas(new HashSet<>());
        adicionarPlataformas(jogo, plataformas);
    }


    @Transactional
    public void adicionarCategorias(Jogo jogo, List<Categoria> categorias) {
        categorias.forEach(jogo::addCategoria);
    }

    @Transactional
    public void removerCategorias(Jogo jogo, List<Categoria> categorias) {
        categorias.forEach(jogo::delCategoria);
    }

    @Transactional
    public void adicionarPlataformas(Jogo jogo, List<Plataforma> plataformas) {
        plataformas.forEach(jogo::addPlataforma);
    }

    @Transactional
    public void removerPlataformas(Jogo jogo, List<Plataforma> plataformas) {
        plataformas.forEach(jogo::delPlataforma);
    }

    @Transactional
    public void remover(Long jogoId) {
        try {
            var jogo = buscarPorId(jogoId);
            repository.delete(jogo);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format("Jogo de id: #%s está em uso. Remova-o da conta dos usuários e tente novamente.", jogoId));
        }
    }


    private String transformarNomeToUriNome(String nome) {
        String stringTransformada = nome.toLowerCase();
        stringTransformada = stringTransformada.replaceAll("[^a-z A-Z0-9]", "");
        stringTransformada = stringTransformada.replaceAll(" +", " ");
        stringTransformada = stringTransformada.replaceAll(" ", "-");

        System.out.println(stringTransformada);
        return stringTransformada;
    }

    private void verificarUriNomeJaCadastrado(String uriNome) {
        var jogo = repository.findByUriNome(uriNome);
        if (jogo.isPresent()) {
            throw new NegocioException(String.format("Jogo '%s' já está cadastrado.", jogo.get().getNome()));
        }
    }
}
