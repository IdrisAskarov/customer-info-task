package com.example.customer.exception;

import java.time.ZonedDateTime;

public class CustomerExceptionApi {
    private String message;
    private ZonedDateTime timestamp;

    public CustomerExceptionApi(String message, ZonedDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
