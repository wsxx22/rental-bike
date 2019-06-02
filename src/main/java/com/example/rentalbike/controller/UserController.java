package com.example.rentalbike.controller;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.app.security.SecurityUtils;
import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.UserMapper;
import com.example.rentalbike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //private CurrentUser currentUser;
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        //this.currentUser = currentUser;
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
    public List<UserDto> findAll (Pageable pageable) {
        return userMapper.toDtoList(userService.findAll(pageable));
    }

    @DeleteMapping("/{username}")
    public long deleteByUsername (@PathVariable String username) {
        return userService.deleteByUsername(username);
    }

//    @GetMapping("/user")
//    public String findByCurrentUser () {
//        return SecurityUtils.getUsername();
//    }

}
