package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.FundRaiseRequestResource;
import com.bedfordshire.helpmebackend.resource.FundRequestResource;
import com.bedfordshire.helpmebackend.service.FundRequestService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/fund-requests")
public class FundRequestController extends ResponseHandler {
    @Autowired
    private FundRequestService fundRequestService;

    @PostMapping("/raise")
    public ResponseEntity<?> raiseFundRequest(@RequestHeader("user") String uuid, @RequestBody FundRequestResource fundRequestResource) throws ParseException {
        return successResponseDataRetrieve(fundRequestService.raiseFundRequest(uuid, fundRequestResource));
    }

    @PostMapping("/donation")
    public ResponseEntity<?> raiseFundRequest(@RequestHeader("user") String uuid, @RequestBody FundRaiseRequestResource fundRaiseRequestResource) throws ParseException {
        fundRequestService.saveDonationDetail(uuid, fundRaiseRequestResource);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/donation/history")
    public ResponseEntity<?> raiseFundRequest(@RequestHeader("user") String uuid) {
        return successResponseDataRetrieve(fundRequestService.getDonationHistory(uuid));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getFundRequestHistory(@RequestHeader("user") String uuid) {
        return successResponseDataRetrieve(fundRequestService.getFundRequestHistory(uuid));
    }

    @GetMapping("/donations/history/{fund_request_uuid}")
    public ResponseEntity<?> getDonationHistoryForFundRequestId(@PathVariable(name = "fund_request_uuid") String uuid) {
        return successResponseDataRetrieve(fundRequestService.getDonationHistoryForFundRequestId(uuid));
    }
}
