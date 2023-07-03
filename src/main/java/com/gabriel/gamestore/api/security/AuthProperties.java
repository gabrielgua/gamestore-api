package com.gabriel.gamestore.api.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("gamestore.auth")
public class AuthProperties {

    @NotNull
    private AuthProperties.Jwt jwt;


    @Getter
    @Setter
    @Validated
    public static class Jwt {
        @NotBlank
        private String secretKey;

        @NotNull
        private Token accessToken;

        @NotNull
        private Token refreshToken;

    }

    @Getter
    @Setter
    @Validated
    public static class Token {
        @NotNull
        private Long expiration;
    }



}
