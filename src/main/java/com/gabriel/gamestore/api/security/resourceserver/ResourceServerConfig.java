package com.gabriel.gamestore.api.security.resourceserver;

import com.gabriel.gamestore.api.security.AuthProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig {

    private static final String[] AUTH_WHITELIST = {
            "/login", "/logout", "/oauth2/login", "/jogos", "/fonts/**", "/css/**", "/images/**"
    };

    @Bean
    public SecurityFilterChain resourceFilterChain(HttpSecurity httpSecurity, AuthProperties properties) throws Exception{
        httpSecurity.formLogin(Customizer.withDefaults())
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .and()
                                .authorizeHttpRequests().anyRequest().authenticated();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        httpSecurity.logout(logoutConfig -> {
            logoutConfig.logoutSuccessHandler((request, response, authentication) -> {
                var returnTo = request.getParameter("returnTo");
                if (!StringUtils.hasText(returnTo)) {
                    returnTo = properties.getProviderUrl();
                }

                response.setStatus(302);
                response.sendRedirect(returnTo);
            })
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies();
        });

        return httpSecurity.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var userAuthorities = jwt.getClaimAsStringList("authorities");

            if (userAuthorities.isEmpty()) {
                userAuthorities = Collections.emptyList();
            }

            var scopesConverter = new JwtGrantedAuthoritiesConverter();
            var scopesAuthorities = scopesConverter.convert(jwt);

            scopesAuthorities.addAll(userAuthorities.stream()
                    .map(SimpleGrantedAuthority::new).toList());

            return scopesAuthorities;
        });

        return converter;
    }
}
