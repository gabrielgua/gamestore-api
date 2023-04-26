package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.exception.PedidoNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Pedido;
import com.gabriel.gamestore.domain.model.StatusPedido;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        var usuario = usuarioService.buscarPorId(pedido.getUsuario().getId());
        var jogos = getJogos(pedido);

        usuarioService.adicionarJogos(usuario, jogos);
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

        var usuario = usuarioService.buscarPorId(pedido.getUsuario().getId());
        var jogos = getJogos(pedido);

        usuarioService.removerJogos(usuario, jogos);
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
        var pedidosComSatusCriado = usuario.getPedidos().stream().filter(p -> p.getStatus().equals(StatusPedido.CRIADO)).toList();


        Set<Jogo> jogosDosPedidosCriadosAnteriores = new HashSet<>();
        pedidosComSatusCriado.forEach(p -> {
            jogosDosPedidosCriadosAnteriores.addAll(p.getJogos());
        });

        var jogosIguaisDosPedidos = jogosDoPedido.stream().filter(jogosDosPedidosCriadosAnteriores::contains).toList();
        var jogosIguaisDosPedidosIds = jogosIguaisDosPedidos.stream().map(Jogo::getId).toList();

        if (!jogosIguaisDosPedidos.isEmpty()) {
            throw new NegocioException(
                    String.format("Usuário '%s' já possui um ou mais pedidos com status 'CRIADO' que contém os seguintes jogos: '%s'"
                            , usuario.getUsername(), jogosIguaisDosPedidosIds));
        }

    }

    private void verificaSePedidoPossuiJogosQueOUsuarioJaPossui(Pedido pedido, Usuario usuario) {
        var jogosDoPedido = getJogos(pedido);
        List<Jogo> jogosIguais = usuario.getJogos().stream().filter(jogosDoPedido::contains).toList();
        List<Long> jogosIguaisIds = jogosIguais.stream().map(Jogo::getId).toList();

        if (!jogosIguais.isEmpty()) {
            throw new NegocioException(
                    String.format("Usuário '%s' já possui os seguintes jogos: '%s'"
                            , usuario.getUsername(), jogosIguaisIds));
        };
    }
}
