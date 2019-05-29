package com.example.rentalbike.service;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.exception.UserNotFound;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll () {
        return userRepository.findAll();
    }

    public User update(String username, User user) {

        User u = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound());

        u.setUsername(user.getUsername() == null ? u.getUsername() : user.getUsername());
        u.setEmail(user.getEmail() == null ? u.getEmail() : user.getEmail());
        u.setPassword(user.getPassword() == null ? u.getPassword() : user.getPassword());
        u.setAccountExpired((Boolean)user.isAccountExpired() == null ? u.isAccountExpired() : user.isAccountExpired());

        return userRepository.save(u);

    }

}
