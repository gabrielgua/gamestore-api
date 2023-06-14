package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.ModoNaoEncontradoException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.model.Modo;
import com.gabriel.gamestore.domain.repository.ModoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModoService {

    private ModoRepository repository;

    @Transactional(readOnly = true)
    public List<Modo> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Modo buscarPorId(Long modoId) {
        return repository.findById(modoId).orElseThrow(() -> new ModoNaoEncontradoException(modoId));
    }

    @Transactional(readOnly = true)
    public List<Modo> buscarVariosPorId(Set<Long> modoIds) {
        return modoIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Modo salvar(Modo modo) {
        return repository.save(modo);
    }

    @Transactional
    public void remover(Long modoId) {
        try {
            var modo = buscarPorId(modoId);
            repository.delete(modo);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new NegocioException(String.format("Modo de id: #%s está em uso. Remova-a de qualquer jogo e tente novamente.", modoId));
        }
    }
}
