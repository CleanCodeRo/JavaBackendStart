package com.utcb.javaBackendStart.auth.services;

import com.utcb.javaBackendStart.shared.exceptions.JwtNotValidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.utcb.javaBackendStart.auth.models.AppUserDetails.rolePrefix;

@Component
public class JwtService {
    private final Key secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public JwtService(@Value("${jwt.secret}") String secretString) {
        this.secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = Map.of(
                "sub", authentication.getName(),
                "iat", new Date(System.currentTimeMillis()),
                "exp", new Date(System.currentTimeMillis() + expiration),
                "aud", "CAMBI_FE",
                "roles", authentication.getAuthorities().stream().map(role -> role.getAuthority().substring(rolePrefix.length())).collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtNotValidException(token);
        }
    }

    public String getUserName(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
