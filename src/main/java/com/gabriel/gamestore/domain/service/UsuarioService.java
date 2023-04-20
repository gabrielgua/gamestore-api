package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.exception.UsuarioNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.TipoUsuario;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

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

    @Transactional
    public Usuario salvar(Usuario usuario) {
        repository.detach(usuario);
        System.out.println(usuario);
        checarEmailAndUsername(usuario);

        if (usuario.isNovo()) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            usuario.setTipo(TipoUsuario.USER);
        }

        return repository.save(usuario);
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
    public void adicionarJogos(Usuario usuario, List<Jogo> jogos) {
        jogos.forEach(usuario::addJogo);
    }

    @Transactional
    public void removerJogos(Usuario usuario, List<Jogo> jogos) {
        jogos.forEach(usuario::delJogo);
    }


    private void checarEmailAndUsername(Usuario usuario) {
        var usuarioExistenteUsername = repository.findByUsername(usuario.getUsername());
        var usuarioExsitenteEmail = repository.findByEmail(usuario.getEmail());

        if (usuarioExistenteUsername.isPresent() && !usuarioExistenteUsername.get().equals(usuario)) {
            throw new NegocioException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        } else if (usuarioExsitenteEmail.isPresent() && !usuarioExsitenteEmail.get().equals(usuario)) {
            throw new NegocioException(String.format("E-mail '%s' já cadastrado.", usuario.getEmail()));
        }
    }


}
