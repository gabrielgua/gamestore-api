package com.gabriel.gamestore.api.controller;


import com.gabriel.gamestore.api.assembler.DesenvolvedoraAssembler;
import com.gabriel.gamestore.api.assembler.JogoAssembler;
import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.JogoResumoModel;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.DesenvolvedoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/desenvolvedoras/{desenvolvedoraId}/jogos")
public class DesenvolvedoraJogoController {

    private final DesenvolvedoraService desenvolvedoraService;
    private final JogoAssembler jogoAssembler;

    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<JogoResumoModel> listarJogos(@PathVariable Long desenvolvedoraId) {
        var desenvolvedora = desenvolvedoraService.buscarPorId(desenvolvedoraId);
        return jogoAssembler.toCollectionResumoModel(desenvolvedora.getJogos().stream().toList());
    }
}
