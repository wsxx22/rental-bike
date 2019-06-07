package com.example.rentalbike.exception;

public class BikeNotFoundException extends RuntimeException {
    public BikeNotFoundException() {
        super("Bike not found.");
    }
}
