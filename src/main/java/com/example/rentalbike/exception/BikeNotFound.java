package com.example.rentalbike.exception;

public class BikeNotFound extends RuntimeException {
    public BikeNotFound() {
        super("Bike not found.");
    }
}
