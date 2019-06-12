package com.example.rentalbike.controller;


import com.example.rentalbike.ApiResponse;
import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.AuthenticatedUserDto;
import com.example.rentalbike.dto.UpdateUserDto;
import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.UserMapper;
import com.example.rentalbike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private CurrentUser currentUser;
    private UserService userService;
    private UserMapper userMapper;


    @Autowired
    public UserController(CurrentUser currentUser, UserService userService, UserMapper userMapper) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/users")
    public UserDto addUser(@RequestBody User user) {
        return userMapper.toDto(userService.addUser(user));
    }

    @PatchMapping("/users/{username}")
    public UserDto updateUser (@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {

        return userMapper.toDto(userService.update(username, updateUserDto));
    }

    @GetMapping("/users")
    public List<UserDto> findAll (Pageable pageable) {
        return userMapper.toDtoList(userService.findAll(pageable));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Object> deleteByUsername (@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.status(200).body(new ApiResponse(200, "account deleted"));
    }

    @GetMapping("/user")
    public AuthenticatedUserDto findByCurrentUser () {
        return userMapper.toAuthenticatedDto(currentUser.getUser());
    }


}
