package com.example.grpc.exceptions;

public class InvalidDateRangeException extends Exception{
    public InvalidDateRangeException(String message) {
        super(message);
    }
}


