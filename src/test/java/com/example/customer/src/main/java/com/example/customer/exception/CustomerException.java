package com.example.customer.exception;

import java.time.ZonedDateTime;

public class CustomerException extends RuntimeException{
    public CustomerException(String message) {
        super(message);
    }
}
