package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("erros")
public class ErrosController {

    @GetMapping("/404")
    public void return404() {
        throw new EntidadeNaoEncontradaException("Entidade não encontrada.");
    }


    @GetMapping("/400")
    public void return400() {
        throw new NegocioException("Bad request.");
    }
}
