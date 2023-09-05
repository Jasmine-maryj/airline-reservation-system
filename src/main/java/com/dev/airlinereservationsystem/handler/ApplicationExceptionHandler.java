package com.dev.airlinereservationsystem.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> resourceNotFoundHandler(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> error = new HashMap<>();
        error.put("message", resourceNotFoundException.getMessage());
        return error;
    }

    @ExceptionHandler(AlreadyBookedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> alreadyBookedException(AlreadyBookedException alreadyBookedException){
        Map<String, String> error = new HashMap<>();
        error.put("message", alreadyBookedException.getMessage());
        return error;
    }
}
