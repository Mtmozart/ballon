package br.com.ballon.infra.security;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.infra.user.AdminEntity;
import br.com.ballon.infra.user.ConsumerEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public <T> String generateToken(T user) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            if (user instanceof ConsumerEntity consumer) {
                return JWT.create()
                        .withIssuer("Ballon")
                        .withSubject(consumer.getEmail())
                        .withClaim("id", consumer.getId().toString())
                        .withExpiresAt(expirationData(72))
                        .sign(algoritmo);

            } else if (user instanceof AdminEntity admin) {
                return JWT.create()
                        .withIssuer("Ballon")
                        .withSubject(admin.getEmail())
                        .withClaim("id", admin.getId().toString())
                        .withExpiresAt(expirationData(24))
                        .sign(algoritmo);
            }

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token jwt.", exception);
        }

        return null;
    }


    private Instant expirationData(int qtd) {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-04:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Ballon")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new BallonException("Token JWT inválido ou expirado!");
        }
    }

    public String getId(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Ballon")
                    .build()
                    .verify(tokenJWT)
                    .getClaim("id").asString();
        } catch (JWTVerificationException exception) {
            throw new BallonException("Token JWT inválido ou expirado!");
        }
    }

    public String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}