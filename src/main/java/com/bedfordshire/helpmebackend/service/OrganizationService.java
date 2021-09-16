package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.model.OrganizationModel;
import com.bedfordshire.helpmebackend.repository.OrganizationRepository;
import com.bedfordshire.helpmebackend.resource.admin.AdminOrganizationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh
 */
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<AdminOrganizationResource> getPendingOrganizations() {
        List<OrganizationModel> approvalPending = organizationRepository.findByStatus("APPROVAL_PENDING");
        return approvalPending.stream().map(organizationModel -> {
            AdminOrganizationResource resource = new AdminOrganizationResource();
            resource.setOrgName(organizationModel.getName());
            resource.setLocation(organizationModel.getLocation());
            resource.setEmail(organizationModel.getUserModel().getEmail());
            resource.setUsername(organizationModel.getUserModel().getName());
            return resource;
        }).collect(Collectors.toList());
    }
}
