package com.example.grpc.exceptions;

public class NoOfferInThisDateException extends Exception {
    public NoOfferInThisDateException(String message) {
        super(message);
    }
}
