package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.exception.UsuarioNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Compra;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.TipoUsuario;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    private static final String AVATAR_API_URL = "https://api.dicebear.com/6.x/bottts/svg?seed=";

    private UsuarioRepository repository;
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }


    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsuarioNaoEncontradoException(username));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        repository.detach(usuario);
        checarUsernameAndEmail(usuario);

        if (usuario.isNovo()) {
            usuario.setAvatarUrl(generateAvatarUrl(usuario.getUsername()));
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            usuario.setTipo(TipoUsuario.USER);
        }

        return repository.save(usuario);
    }

    private String generateAvatarUrl(String username) {
        return AVATAR_API_URL + username + UUID.randomUUID().toString();
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String senhaNova) {
        var usuario = buscarPorId(usuarioId);
        if (!encoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("Senha não coincide com a senha atual do usuário.");
        }
        usuario.setSenha(encoder.encode(senhaNova));
    }

    @Transactional
    public void remover(Long usuarioId) {
        var usuario = buscarPorId(usuarioId);
        repository.delete(usuario);
    }

    @Transactional
    public void adicionarJogoDesejo(Usuario usuario, Jogo jogo) {
        if (verificaSeUsuarioJaPossuiJogo(usuario, jogo)) {
            throw new NegocioException(String.format("'%s' já está na sua biblioteca.", jogo.getNome()));
        }

        usuario.addJogo(jogo);
    }

    private boolean verificaSeUsuarioJaPossuiJogo(Usuario usuario, Jogo jogo) {
        var compras = usuario.getCompras();
        var jogos = compras.stream()
                .map(Compra::getJogo)
                .collect(Collectors.toSet());

        return jogos.contains(jogo);
    }

    @Transactional
    public void removerJogoDesejo(Usuario usuario, Jogo jogo) {
        usuario.delJogo(jogo);
    }

    private void checarUsernameAndEmail(Usuario usuario) {
        if (usernameTaken(usuario)) {
            throw new NegocioException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        }

        if (emailTaken(usuario)) {
            throw new NegocioException(String.format("E-mail '%s' já cadastrado", usuario.getEmail()));
        }
    }

    private boolean usernameTaken(Usuario usuario) {
        var usuarioExistente = repository.findByUsername(usuario.getUsername());
        return usuarioExistente.isPresent() && !isSameUsuario(usuarioExistente.get(), usuario);
    }

    public boolean usernameTaken(String username) {
        var usuario = repository.findByUsername(username);
        return usuario.isPresent();
    }

    public boolean emailTaken(String email) {
        var usuario = repository.findByEmail(email);
        return usuario.isPresent();
    }

    public void tornarAdmin(Usuario usuario) {
        if (isAdmin(usuario)) return;

        usuario.setTipo(TipoUsuario.ADMIN);
        repository.save(usuario);
    }

    public void revogarAdmin(Usuario usuario) {
        if (!isAdmin(usuario)) return;

        usuario.setTipo(TipoUsuario.USER);
        repository.save(usuario);
    }



    private boolean emailTaken(Usuario usuario) {
        var usuarioExistente = repository.findByEmail(usuario.getEmail());
        return usuarioExistente.isPresent() && !isSameUsuario(usuarioExistente.get(), usuario);
    }

    private boolean isSameUsuario(Usuario usuario, Usuario checkUsuario) {
        return usuario.getId().equals(checkUsuario.getId());
    }

    private boolean isAdmin(Usuario usuario) {
        return usuario.getTipo() == TipoUsuario.ADMIN;
    }





}
