package com.example.rentalbike.app.security;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.exception.UserNotFoundException;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserImpl implements CurrentUser {

    private UserRepository userRepository;

    @Autowired
    public CurrentUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {
        return userRepository.findByUsername(SecurityUtils.getUsername()).orElseThrow(() -> new UserNotFoundException());
    }

}
