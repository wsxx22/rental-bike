package com.example.rentalbike.service;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.UpdateUserDto;
import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.exception.UserNotFoundException;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {

        return userRepository.save(user);
    }

    public List<User> findAll (Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or #username == authentication.principal.username)")
    public User update(String username, UpdateUserDto updateUserDto) {

        User u = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        u.setUsername(updateUserDto.getUsername() == null ? u.getUsername() : updateUserDto.getUsername());
        u.setEmail(updateUserDto.getEmail() == null ? u.getEmail() : updateUserDto.getEmail());

        if (currentUser.getUser().getRoles().stream().anyMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
            u.setIsAccountExpired(updateUserDto.getIsAccountExpired() == null ? u.getIsAccountExpired() : updateUserDto.getIsAccountExpired());
            u.setIsAccountLocked(updateUserDto.getIsAccountLocked() == null ? u.getIsAccountLocked() : updateUserDto.getIsAccountLocked());
            u.setIsCredentialsExpired(updateUserDto.getIsCredentialsExpired() == null ? u.getIsCredentialsExpired() : updateUserDto.getIsCredentialsExpired());
            u.setIsEnabled(updateUserDto.getIsEnabled() == null ? u.getIsEnabled() : updateUserDto.getIsEnabled());
        }

        return userRepository.save(u);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or #username == authentication.principal.username)")
    public boolean deleteByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());

        user.setIsAccountLocked(true);
        user.setIsEnabled(false);

        return userRepository.save(user) != null;
    }
}
