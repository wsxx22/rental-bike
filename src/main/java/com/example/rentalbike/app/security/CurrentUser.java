package com.example.rentalbike.app.security;

import com.example.rentalbike.entity.User;

@FunctionalInterface
public interface CurrentUser {
    User getUser();
}
