package com.shermann.park_api.controller.exceptions;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String string) {
        super(string);
    }
}
