package com.example.rentalbike.controller;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.UserMapper;
import com.example.rentalbike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public UserDto addUser(@RequestBody User user) {
        return userMapper.toDto(userService.addUser(user));
    }

    @PatchMapping("/{username}")
    public UserDto updateUser (@PathVariable String username, @RequestBody User user) {

        return userMapper.toDto(userService.update(username, user));
    }

    @GetMapping
    public List<UserDto> findAll () {
        return userMapper.toDtoList(userService.findAll());
    }

}
