package com.example.rentalbike.controller;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.UserMapper;
import com.example.rentalbike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping("/add")
    public UserDto addUser(@RequestBody User user) {
        return userMapper.toDto(userService.addUser(user));
    }
}
