package com.example.grpc.exceptions;

public class ReservationNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public ReservationNotFoundException(String msg) {
        super(msg);
    }
}
