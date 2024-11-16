package com.shermann.park_api.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorMessage(request,HttpStatus.NOT_ACCEPTABLE,"INVALID FIELD", result));
    }

    @ExceptionHandler(EmailUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> DataIntegrityViolationException(RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundExceptions.class)
    public ResponseEntity<ErrorMessage> EntityNotFoundExceptions (RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> PasswordInvalidException (RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }


}
