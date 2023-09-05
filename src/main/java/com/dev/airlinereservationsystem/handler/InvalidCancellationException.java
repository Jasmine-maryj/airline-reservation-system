package com.dev.airlinereservationsystem.handler;

public class InvalidCancellationException extends RuntimeException{
    public InvalidCancellationException(String message){
        super(message);
    }
}
