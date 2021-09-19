package com.bedfordshire.helpmebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lakitha Prabudh
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends RuntimeException{
    public CustomInternalServerException(String message) {
        super(message);
    }

    public CustomInternalServerException(String message, Exception exception) {
        super(message, exception);
    }

    public CustomInternalServerException(Exception exception) {
        super(exception);
    }
}
