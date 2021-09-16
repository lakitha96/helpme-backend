package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.service.OrganizationService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends ResponseHandler {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/organization/pending")
    public ResponseEntity<?> getPendingOrganizations() {
        return successResponseDataRetrieve(organizationService.getPendingOrganizations());
    }
}
