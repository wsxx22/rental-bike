package com.example.rentalbike.service;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.exception.UserNotFoundException;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> findAll (Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public User update(String username, User user) {

        User u = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        u.setUsername(user.getUsername() == null ? u.getUsername() : user.getUsername());
        u.setEmail(user.getEmail() == null ? u.getEmail() : user.getEmail());
        u.setPassword(user.getPassword() == null ? u.getPassword() : user.getPassword());
        u.setIsAccountExpired(user.getIsAccountExpired() == null ? u.getIsAccountExpired() : user.getIsAccountExpired());
        u.setIsAccountLocked(user.getIsAccountLocked() == null ? u.getIsAccountLocked() : user.getIsAccountLocked());
        u.setIsCredentialsExpired(user.getIsCredentialsExpired() == null ? u.getIsCredentialsExpired() : user.getIsCredentialsExpired());
        u.setIsEnabled(user.getIsEnabled() == null ? u.getIsEnabled() : user.getIsEnabled());

        return userRepository.save(u);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public long deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }
}
