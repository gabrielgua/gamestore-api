package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.exception.PedidoNaoEncontradoException;
import com.gabriel.gamestore.domain.model.*;
import com.gabriel.gamestore.domain.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final UsuarioService usuarioService;
    private final JogoService jogoService;
    private final CompraService compraService;

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
        var compras = compraService.salvar(pedido);

//        usuarioService.adicionarCompras(pedido.getUsuario(), compras);

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
        compraService.remover(pedido);
    }

    private void validarPedido(Pedido pedido) {
        var usuario = usuarioService.buscarPorId(pedido.getUsuario().getId());
        var jogosDoPedido = getJogos(pedido);

        verificaSePedidoPossuiJogosIguaisAPedidosAnteriores(pedido, usuario);
        verificaSePedidoPossuiJogosQueOUsuarioJaPossui(pedido, usuario);

        pedido.setUsuario(usuario);
        pedido.setJogos(Set.copyOf(jogosDoPedido));
        pedido.calcularValorTotal();

    }

    private List<Jogo> getJogos(Pedido pedido) {
        var jogosIds = pedido.getJogos().stream()
                .map(Jogo::getId).toList();

        return jogoService.buscarVariosPorId(jogosIds);
    }

    private void verificaSePedidoPossuiJogosIguaisAPedidosAnteriores(Pedido pedido, Usuario usuario) {
        var jogosDoPedido = getJogos(pedido);
        var pedidosComStatusCriado = usuario.getPedidos().stream().filter(p -> p.getStatus().equals(StatusPedido.CRIADO)).toList();


        Set<Jogo> jogosDosPedidosCriadosAnteriores = new HashSet<>();
        pedidosComStatusCriado.forEach(p -> {
            jogosDosPedidosCriadosAnteriores.addAll(p.getJogos());
        });

        var jogosIguaisDosPedidos = jogosDoPedido.stream().filter(jogosDosPedidosCriadosAnteriores::contains).toList();
        var jogosIguaisDosPedidosIds = jogosIguaisDosPedidos.stream().map(Jogo::getId).toList();

        if (!jogosIguaisDosPedidos.isEmpty()) {
            throw new NegocioException(
                    String.format("Usuário já possui um pedido com status 'CRIADO' que contém os seguintes jogos: '%s'"
                            , jogosIguaisDosPedidosIds));
        }

    }

    private void verificaSePedidoPossuiJogosQueOUsuarioJaPossui(Pedido pedido, Usuario usuario) {
        var jogosDoPedido = getJogos(pedido);

        Set<Compra> comprasUsuario = usuario.getCompras();
        List<Jogo> jogosIguais = comprasUsuario.stream()
                .map(Compra::getJogo)
                .filter(jogosDoPedido::contains)
                .toList();
        List<Long> jogosIguaisIds = jogosIguais.stream().map(Jogo::getId).toList();

        if (!jogosIguais.isEmpty()) {
            throw new NegocioException(
                    String.format("Usuário já possui os seguintes jogos: '%s'"
                            , jogosIguaisIds));
        };
    }




}
