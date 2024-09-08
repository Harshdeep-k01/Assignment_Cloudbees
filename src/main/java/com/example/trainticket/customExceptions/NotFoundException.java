package com.example.trainticket.customExceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String message)
    {
        super(message);
    }
}
