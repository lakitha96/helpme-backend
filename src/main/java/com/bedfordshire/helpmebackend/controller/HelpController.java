package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.HelpRequestResource;
import com.bedfordshire.helpmebackend.service.HelpRequestService;
import com.bedfordshire.helpmebackend.service.HelpTypeService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> saveHelpRequest(@RequestHeader("user") String userUuid,@RequestBody HelpRequestResource helpRequestResource) {
        helpRequestService.saveHelpRequest(userUuid, helpRequestResource);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/request")
    public ResponseEntity<?> getHelpRequestByStatus(@RequestParam String status) {
        return successResponseDataRetrieve(helpRequestService.getAllHelpRequestByStatus(status));
    }
}
