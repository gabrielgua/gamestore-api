package com.gabriel.gamestore.api.security.authentication;

import com.gabriel.gamestore.api.assembler.UsuarioAssembler;
import com.gabriel.gamestore.api.model.request.UsuarioComSenhaRequest;
import com.gabriel.gamestore.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;
    private final UsuarioAssembler usuarioAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthModel> authenticate(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthModel> register(@RequestBody @Valid UsuarioComSenhaRequest request) {
        var usuario = usuarioAssembler.toEntity(request);
        return ResponseEntity.ok(authService.register(usuarioService.salvar(usuario)));
    }
}
