package com.shermann.park_api.controller.exceptions;

public class EmailUniqueViolationException extends RuntimeException {
    public EmailUniqueViolationException(String format) {
        super(format);
    }
}
