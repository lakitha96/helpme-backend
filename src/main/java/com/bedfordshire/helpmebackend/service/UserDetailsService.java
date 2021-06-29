package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.config.JwtAuthenticationConfig;
import com.bedfordshire.helpmebackend.config.JwtGenerator;
import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.jwt.JwtUserDetailService;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.AuthTokenResponse;
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

    public AuthTokenResponse userRegister(UserResource user) {
        UserModel userModelByEmail = userRepository.findByEmail(user.getEmail());
        if (userModelByEmail != null) {
            throw new CustomBadRequestException("user email already exist.");
        }
        UserModel newUserModel = new UserModel();
        newUserModel.setName(user.getUsername());
        newUserModel.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUserModel.setEmail(user.getEmail());
        newUserModel.setRole(ExampleParam.USER_NORMAL);
        newUserModel.setUuid(UUID.randomUUID().toString());
        newUserModel.setStatus(true);
        UserModel savedUserModel = userRepository.save(newUserModel);

        AuthTokenResponse authTokenResponse = new AuthTokenResponse();
        authTokenResponse.setAccessToken(createAccessToken(getUserResource(savedUserModel)));
        authTokenResponse.setRefreshToken(createRefreshToken(getUserResource(savedUserModel)));
        return authTokenResponse;
    }

    private UserResource getUserResource(UserModel userModel) {
        UserResource userResource = new UserResource();
        userResource.setActive(userModel.isStatus());
        userResource.setUuid(userModel.getUuid());
        userResource.setEmail(userModel.getEmail());
        userResource.setUsername(userModel.getName());
        userResource.setRole(userModel.getRole());
        return userResource;
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