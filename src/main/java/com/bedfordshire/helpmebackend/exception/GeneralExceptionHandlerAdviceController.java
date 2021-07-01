package com.bedfordshire.helpmebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GeneralExceptionHandlerAdviceController {


    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    GeneralResponse handleBadRequest(CustomBadRequestException exception,
                                     HttpServletRequest request) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setLastModified(new Date());
        generalResponse.setMessage(exception.getMessage());
        generalResponse.setStatusInfo(HttpStatus.BAD_REQUEST);
        return generalResponse;
    }
}
