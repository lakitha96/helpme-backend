package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.config.JwtAuthenticationConfig;
import com.bedfordshire.helpmebackend.config.JwtGenerator;
import com.bedfordshire.helpmebackend.jwt.JwtUserDetailService;
import com.bedfordshire.helpmebackend.model.User;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.UserResource;
import com.bedfordshire.helpmebackend.utils.ExampleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Service
public class UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtAuthenticationConfig jwtAuthenticationConfig;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    public User userRegister(UserResource user) {
        User newUser = new User();
        newUser.setName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRole(ExampleParam.USER_NORMAL);
        newUser.setUuid(UUID.randomUUID().toString());
        return userRepository.save(newUser);
    }

    private String createAccessToken(UserResource userResource) {

        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ExampleParam.ROLE_PREFIX + userResource.getRole()));

        return JwtGenerator.generateAccessJWT(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getAccessTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }

    private String createRefreshToken(UserResource userResource) {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ExampleParam.ROLE_PREFIX + userResource.getRole()));
        return JwtGenerator.generateRefreshToken(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getRefreshTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }
}