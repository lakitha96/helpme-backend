package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.model.OrganizationModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.OrganizationRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.admin.AdminOrganizationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh
 */
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;

    public List<AdminOrganizationResource> getPendingOrganizations() {
        List<OrganizationModel> approvalPending = organizationRepository.findByStatus("APPROVAL_PENDING");
        return approvalPending.stream().map(organizationModel -> {
            AdminOrganizationResource resource = new AdminOrganizationResource();
            resource.setOrgName(organizationModel.getName());
            resource.setLocation(organizationModel.getLocation());
            resource.setEmail(organizationModel.getUserModel().getEmail());
            resource.setUsername(organizationModel.getUserModel().getName());
            resource.setUuid(organizationModel.getUserModel().getUuid());
            return resource;
        }).collect(Collectors.toList());
    }

    public List<AdminOrganizationResource> getAllOrganizations() {
        List<OrganizationModel> allOrganization = organizationRepository.findByStatus("APPROVED");
        return allOrganization.stream().map(organizationModel -> {
            AdminOrganizationResource resource = new AdminOrganizationResource();
            resource.setOrgName(organizationModel.getName());
            resource.setLocation(organizationModel.getLocation());
            resource.setEmail(organizationModel.getUserModel().getEmail());
            resource.setUsername(organizationModel.getUserModel().getName());
            resource.setUuid(organizationModel.getUserModel().getUuid());
            return resource;
        }).collect(Collectors.toList());
    }

    public void approveOrganization(String uuid) {
        UserModel userModel = userRepository.findByUuid(uuid);
        Optional<OrganizationModel> organizationModelOptional = organizationRepository.findByUserModel(userModel);
        if (organizationModelOptional.isPresent()) {
            userModel.setRole("ORGANIZATION");
            OrganizationModel organizationModel = organizationModelOptional.get();
            organizationModel.setStatus("APPROVED");
            userRepository.save(userModel);
            organizationRepository.save(organizationModel);
        }
    }

    public void temporaryBlock(String uuid) {
        UserModel userModel = userRepository.findByUuid(uuid);
        Optional<OrganizationModel> organizationModelOptional = organizationRepository.findByUserModel(userModel);
        if (organizationModelOptional.isPresent()) {
            userModel.setRole("PENDING_ORGANIZATION");
            OrganizationModel organizationModel = organizationModelOptional.get();
            organizationModel.setStatus("APPROVAL_PENDING");
            userRepository.save(userModel);
            organizationRepository.save(organizationModel);
        }
    }
}
