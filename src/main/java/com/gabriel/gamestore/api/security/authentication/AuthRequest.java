package com.gabriel.gamestore.api.security.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String senha;
}
