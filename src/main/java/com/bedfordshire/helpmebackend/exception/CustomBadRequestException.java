package com.bedfordshire.helpmebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lakitha Prabudh
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException(String message) {
        super(message);
    }

    public CustomBadRequestException(String message, Exception exception) {
        super(message, exception);
    }

    public CustomBadRequestException(Exception exception) {
        super(exception);
    }
}
