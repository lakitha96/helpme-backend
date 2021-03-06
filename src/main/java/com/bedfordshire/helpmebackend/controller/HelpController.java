package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.HelpRequestResource;
import com.bedfordshire.helpmebackend.service.HelpRequestService;
import com.bedfordshire.helpmebackend.service.HelpTypeService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/helps")
public class HelpController extends ResponseHandler {
    @Autowired
    private HelpTypeService helpTypeService;
    @Autowired
    private HelpRequestService helpRequestService;

    @GetMapping("/type")
    public ResponseEntity<?> getHelpTypes() {
        return successResponseDataRetrieve(helpTypeService.getAllValidTypes());
    }

    @PostMapping("/request")
    public ResponseEntity<?> saveHelpRequest(@RequestHeader("user") String userUuid,
                                             @RequestBody HelpRequestResource helpRequestResource,
                                             @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        helpRequestService.saveHelpRequest(userUuid, helpRequestResource, multipartFile);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/request")
    public ResponseEntity<?> getHelpRequestByStatus(@RequestParam String status) {
        return successResponseDataRetrieve(helpRequestService.getAllHelpRequestByStatus(status));
    }

    @GetMapping("/request/pending")
    public ResponseEntity<?> getAllPendingHelpRequests() {
        return successResponseDataRetrieve(helpRequestService.getAllPendingHelpRequests());
    }

    @GetMapping("/request/ongoing")
    public ResponseEntity<?> getAllOngoingHelpRequests() {
        return successResponseDataRetrieve(helpRequestService.getAllOngoingHelpRequests());
    }

    @GetMapping("/request/ongoing/{uuid}")
    public ResponseEntity<?> getOngoingHelpRequestByUuid(@PathVariable String uuid) {
        return successResponseDataRetrieve(helpRequestService.getOngoingHelpRequestByUuid(uuid));
    }

    @GetMapping("/affected/location")
    public ResponseEntity<?> getAllOngoingHelpRequestsMapLocations() {
        return successResponseDataRetrieve(helpRequestService.getAllOngoingHelpRequestsMapLocations());
    }
}
