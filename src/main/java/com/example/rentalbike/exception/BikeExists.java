package com.example.rentalbike.exception;

public class BikeExists extends RuntimeException {
    public BikeExists() {
        super("Bike exists in database");
    }
}
