package com.example.grpc.exceptions;
public class OfferNotFoundException extends Exception {
    public OfferNotFoundException(String message) {
        super(message);
    }
}
