package com.bedfordshire.helpmebackend.config;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Lakitha Prabudh
 */
@Component
public class JwtAuthenticationConfig implements Serializable {

    private int accessTokenExpiration = 31536000;
    private int refreshTokenExpiration = 157680000;
    private String secret = "c74d63e5152c4a8a956832f086645bd0";

    public int getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public int getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public String getSecret() {
        return secret;
    }
}