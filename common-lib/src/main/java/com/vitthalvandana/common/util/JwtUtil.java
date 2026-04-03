package com.vitthalvandana.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class JwtUtil {

    @ConfigProperty(name = "jwt.secret", defaultValue = "your-secret-key-change-in-production")
    String jwtSecret;

    @ConfigProperty(name = "jwt.issuer", defaultValue = "vitthal-vandana")
    String jwtIssuer;

    @ConfigProperty(name = "jwt.expiration.hours", defaultValue = "24")
    int expirationHours;

    public String generateToken(String userId, String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer(jwtIssuer)
                    .withSubject(userId)
                    .withClaim("email", email)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (long) expirationHours * 60 * 60 * 1000))
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid token", e);
        }
    }

    public String getUserIdFromToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getSubject();
    }

    public String getEmailFromToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaim("email").asString();
    }
}