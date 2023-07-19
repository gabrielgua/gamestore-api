package com.gabriel.gamestore.api.security.jwt;

import com.gabriel.gamestore.api.security.AuthProperties;
import com.gabriel.gamestore.api.security.exception.InvalidTokenException;
import com.gabriel.gamestore.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final AuthProperties properties;

    public String generateToken(Usuario usuario) {
        return generateToken(new HashMap<>(), usuario);
    }

    public String generateToken(Map<String, Object> extraClaims, Usuario usuario) {
        return buildToken(extraClaims, usuario.getUsername(), properties.getJwt().getAccessToken().getExpiration());
    }

    public String generateRefreshToken(Usuario usuario) {
        var accessTokenExpiration = properties.getJwt().getAccessToken().getExpiration();
        var refreshTokenExpiration = properties.getJwt().getRefreshToken().getExpiration();

        return buildToken(new HashMap<>(), usuario.getUsername(), accessTokenExpiration + refreshTokenExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, String subject, long expiration) {
        var nowMillis = System.currentTimeMillis();

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String subject) {
        final String username = extractUsername(token);
        return username.equals(subject) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new InvalidTokenException();
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getJwt().getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
