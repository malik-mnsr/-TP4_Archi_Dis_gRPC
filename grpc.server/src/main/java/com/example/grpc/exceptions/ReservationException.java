package com.example.grpc.exceptions;

public class ReservationException extends Exception{
    private static final long serialVersionUID = 1L;

    public ReservationException(String msg) {
        super(msg);
    }
}
