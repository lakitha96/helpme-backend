package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.config.JwtAuthenticationConfig;
import com.bedfordshire.helpmebackend.config.JwtGenerator;
import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.OrganizationModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.OrganizationRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.AuthTokenResponse;
import com.bedfordshire.helpmebackend.resource.OrganizationResource;
import com.bedfordshire.helpmebackend.resource.UserResource;
import com.bedfordshire.helpmebackend.utils.ExampleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtAuthenticationConfig jwtAuthenticationConfig;
    @Autowired
    private OrganizationRepository organizationRepository;

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

    public AuthTokenResponse registerOrganization(OrganizationResource organizationResource) {
        UserResource userResource = organizationResource.getUserResource();
        UserModel userModelByEmail = userRepository.findByEmail(userResource.getEmail());
        Optional<OrganizationModel> organizationModelOptional = organizationRepository.findByUserModel(userModelByEmail);
        if (organizationModelOptional.isPresent()) {
            throw new CustomBadRequestException("Organization email already exist.");
        }

        if (userModelByEmail != null) {
            throw new CustomBadRequestException("Normal user cannot create organization account.");
        }

        UserModel newUserModel = new UserModel();
        newUserModel.setName(userResource.getUsername());
        newUserModel.setPassword(bcryptEncoder.encode(userResource.getPassword()));
        newUserModel.setEmail(userResource.getEmail());
        newUserModel.setRole(ExampleParam.USER_PENDING_ORGANIZATION);
        newUserModel.setUuid(UUID.randomUUID().toString());
        newUserModel.setStatus(true);

        UserModel savedUserModel = userRepository.save(newUserModel);

        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setUserModel(savedUserModel);
        organizationModel.setLocation(organizationResource.getLocation());
        organizationModel.setName(organizationResource.getOrganizationName());
        organizationModel.setStatus("APPROVAL_PENDING");
        organizationRepository.save(organizationModel);

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