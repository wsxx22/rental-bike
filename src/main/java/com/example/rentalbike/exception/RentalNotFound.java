package com.example.rentalbike.exception;

public class RentalNotFound extends RuntimeException {
    public RentalNotFound() {
        super("Rental not found");
    }
}
