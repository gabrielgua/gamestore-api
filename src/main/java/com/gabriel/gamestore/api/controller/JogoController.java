package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.service.JogoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private JogoService service;

    @GetMapping
    public List<Jogo> listar() {
        return service.listar();
    }
}
