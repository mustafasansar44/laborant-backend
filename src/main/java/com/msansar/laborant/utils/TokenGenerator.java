package com.msansar.laborant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.msansar.laborant.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Configuration
public class TokenGenerator {

    @Value("${JWT_KEY}")
    private String JWT_KEY;

    @Value("${JWT_ISSUER}")
    private String JWT_ISSUER;

    @Value("${JWT_EXPIRES_MINUTE}")
    private int JWT_EXPIRES_MINUTE;

    // JWT'yi oluşturma metodu
    public String generateToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(username)
                .withIssuer(JWT_ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis()+JWT_EXPIRES_MINUTE*60*1000))
                .sign(Algorithm.HMAC256(JWT_KEY.getBytes()));

    }

    // JWT'yi doğrulama metodu
    public DecodedJWT validateToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_KEY.getBytes())).build();
        try {
            return jwtVerifier.verify(token);
        } catch (Exception ex) {
            throw new NotFoundException("Token bulunamadı!");
        }
    }


}
