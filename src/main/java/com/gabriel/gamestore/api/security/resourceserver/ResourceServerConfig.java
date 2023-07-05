package com.gabriel.gamestore.api.security.resourceserver;

import com.gabriel.gamestore.api.security.AuthProperties;
import com.gabriel.gamestore.api.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authProvider;

    private static final String[] AUTH_WHITELIST = {
            "/login", "/logout", "/auth/**", "/fonts/**", "/css/**", "/images/**"
    };

    @Bean
    public SecurityFilterChain resourceFilterChain(HttpSecurity httpSecurity, AuthProperties properties) throws Exception{
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(HttpMethod.GET, "/jogos", "/jogos/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/plataformas", "/plataformas/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/categorias", "/categorias/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/modos", "/modos/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/desenvolvedoras", "/desenvolvedoras/*", "/desenvolvedoras/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/formas-pagamento", "/formas-pagamento/*").permitAll();
                })
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//        httpSecurity.logout(logoutConfig -> {
//            logoutConfig.logoutSuccessHandler((request, response, authentication) -> {
//                var returnTo = request.getParameter("returnTo");
//                if (!StringUtils.hasText(returnTo)) {
//                    returnTo = properties.getProviderUrl();
//                }
//
//                response.setStatus(302);
//                response.sendRedirect(returnTo);
//            })
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies();
//        });

        return httpSecurity.build();
    }

//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        var converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            var userAuthorities = jwt.getClaimAsStringList("authorities");
//
//            if (userAuthorities.isEmpty()) {
//                userAuthorities = Collections.emptyList();
//            }
//
//            var scopesConverter = new JwtGrantedAuthoritiesConverter();
//            var scopesAuthorities = scopesConverter.convert(jwt);
//
//            scopesAuthorities.addAll(userAuthorities.stream()
//                    .map(SimpleGrantedAuthority::new).toList());
//
//            return scopesAuthorities;
//        });
//
//        return converter;
//    }
}
