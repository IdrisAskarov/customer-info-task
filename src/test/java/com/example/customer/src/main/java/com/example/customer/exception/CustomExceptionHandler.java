package com.example.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {CustomerException.class})
    public ResponseEntity<?> handleCustomerException(CustomerException e, WebRequest request) {
        CustomerExceptionApi customerExceptionApi = new CustomerExceptionApi(e.getMessage(), ZonedDateTime.now());
        return new ResponseEntity<>(customerExceptionApi, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
