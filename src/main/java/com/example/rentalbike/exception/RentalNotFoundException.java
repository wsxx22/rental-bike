package com.example.rentalbike.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException() {
        super("Rental not found");
    }
}
