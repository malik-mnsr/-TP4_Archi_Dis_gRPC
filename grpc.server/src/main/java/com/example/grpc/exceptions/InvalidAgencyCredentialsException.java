package com.example.grpc.exceptions;
public class InvalidAgencyCredentialsException extends Exception {
    public InvalidAgencyCredentialsException(String message) {
        super(message);
    }
}
