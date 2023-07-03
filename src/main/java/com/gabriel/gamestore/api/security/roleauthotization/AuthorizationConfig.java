package com.gabriel.gamestore.api.security.roleauthotization;

import com.gabriel.gamestore.domain.service.PedidoService;
import com.gabriel.gamestore.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        var username = getAuthentication().getName();
        return usuarioService.buscarPorUsername(username).getId();
    }

    public boolean isAutenticado() {
        return getAuthentication().isAuthenticated();
    }

    public boolean temAuthority(String authority) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }

    public boolean temEscopoLeitura() {
        return temAuthority("SCOPE_READ");
    }

    public boolean temEscopoEscrita() {
        return temAuthority("SCOPE_WRITE");
    }

    public boolean isAdmin() {
        return temAuthority("ADMIN");
    }

    public boolean isUser() {
        return temAuthority("USER");
    }

    private boolean usuarioAutenticadoIgualA(Long usuarioId) {
        return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
    }

    // Geral
    public boolean podeGerenciarOuConsultarRecursosProtegidosGerais() {
        return temEscopoEscrita() && isAdmin() && isAutenticado();
    }


    // Usuários
    public boolean podeBuscarUsuario(Long usuarioId) {
        return temEscopoLeitura() && (isAdmin() || usuarioAutenticadoIgualA(usuarioId));
    }

    public boolean podeGerenciarUsuario(Long usuarioId) {
        return temEscopoEscrita() && (isAdmin() || usuarioAutenticadoIgualA(usuarioId));
    }

    public boolean podeAlterarPropriaSenha(Long usuarioId) {
        return usuarioAutenticadoIgualA(usuarioId);
    }

    // Usuários Jogos
    public boolean podeListarJogosDoUsuario(Long usuarioId) {
        return temEscopoLeitura() && (isAdmin() || usuarioAutenticadoIgualA(usuarioId));
    }
    public boolean podeGerenciarJogosDoUsuario(Long usuarioId) {
        return temEscopoEscrita() && isAdmin();
    }

    // Pedidos
    private boolean isResponsavelPorPedido(String codigoPedido, Long usuarioId) {
        var pedido = pedidoService.buscarPorCodigo(codigoPedido);

        return pedido.getUsuario().getId().equals(usuarioId);
    }

    public boolean podeConsultarPropriosPedidos(Long usuarioId) {
        return temEscopoLeitura() && (isAdmin() || usuarioAutenticadoIgualA(usuarioId));
    }

    public boolean podeAdicionarPedido() {
        return temEscopoEscrita() && isAutenticado() && (isAdmin() || isUser());
    }
    public boolean podeConfirmarPedido(String codigoPedido) {
        return podeGerenciarOuConsultarRecursosProtegidosGerais() || isResponsavelPorPedido(codigoPedido, getUsuarioId());
    }

    public boolean podeCancelarPedido(String codigoPedido) {
        return podeGerenciarOuConsultarRecursosProtegidosGerais() || isResponsavelPorPedido(codigoPedido, getUsuarioId());
    }

    public boolean podeReembolsarPedido(String codigoPedido) {
        return temEscopoEscrita() && isResponsavelPorPedido(codigoPedido, getUsuarioId());
    }
}
