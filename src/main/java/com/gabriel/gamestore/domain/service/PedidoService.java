package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.PedidoNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository repository;
    private UsuarioService usuarioService;
    private JogoService jogoService;

    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long pedidoId) {
        return repository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorCodigo(String codigoPedido) {
        return repository.findByCodigo(codigoPedido).orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }

    @Transactional
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        return repository.save(pedido);
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        var pedido = buscarPorCodigo(codigoPedido);
        pedido.confirmarPedido();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        var pedido = buscarPorCodigo(codigoPedido);
        pedido.cancelarPedido();
    }

    @Transactional
    public void reembolsar(String codigoPedido) {
        var pedido = buscarPorCodigo(codigoPedido);
        pedido.reembolsarPedido();
    }

    private void validarPedido(Pedido pedido) {
        var usuario = usuarioService.buscarPorId(pedido.getUsuario().getId());
        var jogosIds = pedido.getJogos().stream()
                .map(Jogo::getId).toList();

        var jogos = jogoService.buscarVariosPorId(jogosIds);

        pedido.setUsuario(usuario);
        pedido.setJogos(Set.copyOf(jogos));
        pedido.calcularValorTotal();

    }


}
