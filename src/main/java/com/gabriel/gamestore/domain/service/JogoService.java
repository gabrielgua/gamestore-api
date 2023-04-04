package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JogoService {

    private JogoRepository repository;

    public List<Jogo> listar() {
        return repository.findAll();
    }
}
