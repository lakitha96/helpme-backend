package com.bedfordshire.helpmebackend.config;

import com.bedfordshire.helpmebackend.utils.ExampleParam;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh
 */
public class JwtGenerator {
    public static String generateAccessJWT(String subject, String uuid, Collection<? extends GrantedAuthority> authorities, int expiration, String secret) {
        Instant now = Instant.now();
        return Jwts.builder().setSubject(subject)
                .claim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).claim("ud", uuid)
                .claim("type", ExampleParam.JWT_TOKEN_TYPE_ACCESS).compact();
    }


    public static String generateRefreshToken(String subject, String uuid, Collection<? extends GrantedAuthority> authorities, int expiration, String secret) {
        Instant now = Instant.now();
        return Jwts.builder().setSubject(subject).claim("authorities", authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).claim("ud", uuid)
                .claim("type", ExampleParam.JWT_TOKEN_TYPE_REFRESH).compact();
    }
}
