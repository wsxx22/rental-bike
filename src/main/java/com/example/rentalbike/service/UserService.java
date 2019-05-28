package com.example.rentalbike.service;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User update(String username, User user) {

        User u = userRepository.findByUsername(username).orElseThrow();
        
        if (user.getEmail() != null) {
            u.setEmail(user.getEmail());
        }

        //.
        //.
        //.

        return userRepository.save(u);

    }

}
