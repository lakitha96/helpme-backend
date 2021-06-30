package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.FundRequestResource;
import com.bedfordshire.helpmebackend.service.FundRequestService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/fund-requests")
public class FundRequestController extends ResponseHandler {
    @Autowired
    private FundRequestService fundRequestService;

    @PostMapping("/raise")
    public ResponseEntity<?> raiseFundRequest(@RequestHeader("user") String uuid, @RequestBody FundRequestResource fundRequestResource) {
        return successResponseDataRetrieve(fundRequestService.raiseFundRequest(uuid, fundRequestResource));
    }
}
