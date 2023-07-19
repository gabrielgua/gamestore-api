package com.gabriel.gamestore.api.security.authentication;

import com.gabriel.gamestore.api.security.exception.InvalidTokenException;
import com.gabriel.gamestore.api.security.jwt.JwtService;
import com.gabriel.gamestore.domain.model.Usuario;
import com.gabriel.gamestore.domain.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    public AuthModel register(Usuario usuario) {
        var accessToken = jwtService.generateToken(setExtraDefaultClaims(usuario), usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);

        return AuthModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthModel authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getSenha())
        );

        var usuario = usuarioService.buscarPorUsername(request.getUsername());
        var accessToken = jwtService.generateToken(setExtraDefaultClaims(usuario), usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);

        return AuthModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthModel refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException();
        }

        refreshToken = authHeader.substring(7);

        username = jwtService.extractUsername(refreshToken);


        var usuario = usuarioService.buscarPorUsername(username);
        if (!jwtService.isTokenValid(refreshToken, usuario.getUsername())) {
            throw new InvalidTokenException();
        }

        var accessToken = jwtService.generateToken(setExtraDefaultClaims(usuario), usuario);
        return AuthModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Map<String, Object> setExtraDefaultClaims(Usuario usuario) {
        // default claims are userId and user authorities

        var extraClaims = new HashMap<String, Object>();
        extraClaims.put("userId", usuario.getId());
        extraClaims.put("authorities", List.of(usuario.getTipo()));

        return extraClaims;
    }
}
