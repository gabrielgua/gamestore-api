package com.gabriel.gamestore.api.security.userdetails;

import com.gabriel.gamestore.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var usuario = repository.findByUsername(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        var simpleGrantedAuthority = new SimpleGrantedAuthority(usuario.getTipo().name());
        return new User(usuario.getUsername(), usuario.getSenha(), List.of(simpleGrantedAuthority));
    }
}
