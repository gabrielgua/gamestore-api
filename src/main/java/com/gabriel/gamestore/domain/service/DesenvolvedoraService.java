package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.DesenvolvedoraNaoEncontradaException;
import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.model.Desenvolvedora;
import com.gabriel.gamestore.domain.repository.DesenvolvedoraRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DesenvolvedoraService {

    private DesenvolvedoraRepository repository;


    @Transactional(readOnly = true)
    public List<Desenvolvedora> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Desenvolvedora buscarPorId(Long desenvolvedoraId) {
        return repository.findById(desenvolvedoraId).orElseThrow(() -> new DesenvolvedoraNaoEncontradaException(desenvolvedoraId));
    }

    @Transactional
    public Desenvolvedora salvar(Desenvolvedora desenvolvedora) {
        return repository.save(desenvolvedora);
    }

    @Transactional
    public void remover(Desenvolvedora desenvolvedora) {
        try {
            repository.delete(desenvolvedora);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(
                    String.format("Desenvolvedora '#%s, %s' está em uso e não pode ser removida."
                            , desenvolvedora.getId(), desenvolvedora.getNome()));
        }
    }
}

