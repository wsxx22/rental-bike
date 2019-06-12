package com.example.rentalbike.app.security;

import com.example.rentalbike.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;

@FunctionalInterface
public interface CurrentUser {

    @PreAuthorize("isAuthenticated()")
    User getUser();

}
