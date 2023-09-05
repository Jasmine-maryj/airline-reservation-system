package com.dev.airlinereservationsystem.handler;

public class AlreadyBookedException extends RuntimeException{

    public AlreadyBookedException(String message){
        super(message);
    }
}
