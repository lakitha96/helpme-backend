package com.bedfordshire.helpmebackend.utils;

import com.bedfordshire.helpmebackend.exception.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Lakitha Prabudh
 */
public class ResponseHandler {
    public ResponseEntity<?> successResponseDataSaving(Object object) {
        return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, object, "Successfully data saved."));
    }

    public ResponseEntity<?> successResponseDataRetrieve(Object object) {
        return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, object, "Success"));
    }
}
