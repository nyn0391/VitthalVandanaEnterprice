package com.vitthalvandana.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Date;

@ApplicationScoped
@Startup
public class JwtUtil {

    @ConfigProperty(name = "jwt.secret", defaultValue = "your-secret-key-change-this-in-production")
    String jwtSecret;

    @ConfigProperty(name = "jwt.expiration", defaultValue = "86400000")
    long jwtExpiration;

    public String generateToken(String userId, String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            Date now = new Date();
            Date expiresAt = new Date(now.getTime() + jwtExpiration);

            return JWT.create()
                    .withSubject(userId)
                    .withClaim("email", email)
                    .withIssuedAt(now)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public String validateToken(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT token", e);
        }
    }

    public String getEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("email")
                    .asString();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT token", e);
        }
    }
}
