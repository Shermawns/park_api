package com.shermann.park_api.controller.exceptions;

public class EntityNotFoundExceptions extends RuntimeException {
    public EntityNotFoundExceptions(String string) {
        super(string);
    }
}
