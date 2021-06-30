package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.OrganizationResource;
import com.bedfordshire.helpmebackend.resource.UserResource;
import com.bedfordshire.helpmebackend.service.RegistrationService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh
 */
@RestController
@CrossOrigin
public class RegistrationController extends ResponseHandler {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserResource user) {
        return successResponseDataRetrieve(registrationService.userRegister(user));
    }

    @RequestMapping(value = "/organization/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveOrganization(@RequestBody OrganizationResource organizationResource) {
        return successResponseDataRetrieve(registrationService.registerOrganization(organizationResource));
    }
}