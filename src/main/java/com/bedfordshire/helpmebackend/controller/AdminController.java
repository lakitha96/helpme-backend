package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.service.FundRequestService;
import com.bedfordshire.helpmebackend.service.HelpRequestService;
import com.bedfordshire.helpmebackend.service.OrganizationService;
import com.bedfordshire.helpmebackend.service.UserService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends ResponseHandler {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private HelpRequestService helpRequestService;
    @Autowired
    private FundRequestService fundRequestService;

    @GetMapping("/organization/pending")
    public ResponseEntity<?> getPendingOrganizations() {
        return successResponseDataRetrieve(organizationService.getPendingOrganizations());
    }

    @GetMapping("/organization/all")
    public ResponseEntity<?> getAllOrganizations() {
        return successResponseDataRetrieve(organizationService.getAllOrganizations());
    }

    @PostMapping("/organization/approve/{uuid}")
    public ResponseEntity<?> getPendingOrganizations(@PathVariable String uuid) {
        organizationService.approveOrganization(uuid);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/organization/block/{uuid}")
    public ResponseEntity<?> temporaryBlock(@PathVariable String uuid) {
        organizationService.temporaryBlock(uuid);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers() {
        return successResponseDataRetrieve(userService.getAllUsers());
    }

    @GetMapping("/help-requests/all")
    public ResponseEntity<?> getAllHelpRequests() {
        return successResponseDataRetrieve(helpRequestService.getAllHelpRequests());
    }

    @GetMapping("/fund-requests/all")
    public ResponseEntity<?> getAllFundRequest() {
        return successResponseDataRetrieve(fundRequestService.getAllFundRequest());
    }

    @GetMapping("/donations/history/{fund_request_uuid}")
    public ResponseEntity<?> getDonationHistoryForFundRequestId(@PathVariable(name = "fund_request_uuid") String uuid) {
        return successResponseDataRetrieve(fundRequestService.getDonationHistoryForFundRequestId(uuid));
    }
}
