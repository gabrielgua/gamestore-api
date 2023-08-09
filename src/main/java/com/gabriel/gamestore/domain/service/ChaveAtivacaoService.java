package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.ChaveAtivacao;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.repository.ChaveAtivacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChaveAtivacaoService {

    private final ChaveAtivacaoRepository repository;

    @Transactional
    public void salvar(Pedido pedido) {
        pedido.getJogos().forEach(jogo -> {
            var chaveAtivacao = new ChaveAtivacao();
            chaveAtivacao.setPedido(pedido);
            chaveAtivacao.setJogo(jogo);
            chaveAtivacao.setChave(gerarChaveAtivacao());
            repository.save(chaveAtivacao);
        });
    }



    private String gerarChaveAtivacao() {
        return UUID.randomUUID().toString();
    }
}
