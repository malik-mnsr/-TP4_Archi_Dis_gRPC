package com.example.grpc.exceptions;
public class NoAvailabilityException extends Exception {
    public NoAvailabilityException(String message) {
        super(message);
    }
}
