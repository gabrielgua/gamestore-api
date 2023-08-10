package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Compra;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository repository;
    private final UsuarioService usuarioService;

    @Transactional
    public List<Compra> salvar(Pedido pedido) {
        var usuario = pedido.getUsuario();
        List<Compra> compras = pedido.getJogos()
                .stream()
                .map(jogo -> {
            return new Compra(jogo, usuario, pedido, gerarChaveAtivacao());
        }).toList();

        return repository.saveAll(compras);
    }


    @Transactional
    public void remover(Pedido pedido) {
        var usuario = pedido.getUsuario();
        var comprasDeletar = usuario.getCompras()
                .stream()
                .filter(c -> c.getCodigoPedido().equals(pedido.getCodigo()))
                .toList();

        repository.deleteAll(comprasDeletar);
    }

    private String gerarChaveAtivacao() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";
        final int size = 15;

        StringBuilder chave = new StringBuilder();
        for (int i = 0; i < size; i++) {
            chave.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        chave.insert(5, "-");
        chave.insert(11, "-");

        return chave.toString();
    }
}
