package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.UsuarioNaoEncontradoException;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository repository;

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
        return repository.save(usuario);
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




}
