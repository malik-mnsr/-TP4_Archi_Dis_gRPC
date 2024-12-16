package com.example.grpc.exceptions;
public class ReservationFailedException extends Exception {
    public ReservationFailedException(String message) {
        super(message);
    }
}