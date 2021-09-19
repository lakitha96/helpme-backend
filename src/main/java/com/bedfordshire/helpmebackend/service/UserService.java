package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.admin.AdminUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<AdminUserResource> getAllUsers() {
        List<UserModel> all = userRepository.findAll();
        return all.stream().map(userModel -> {
            AdminUserResource resource = new AdminUserResource();
            resource.setName(userModel.getName());
            resource.setRole(userModel.getRole());
            resource.setEmail(userModel.getEmail());
            resource.setStatus(userModel.isStatus() ? "ACTIVE" : "INACTIVE");
            return resource;
        }).collect(Collectors.toList());
    }
}
