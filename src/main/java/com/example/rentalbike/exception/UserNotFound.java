package com.example.rentalbike.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found!");
    }
}
