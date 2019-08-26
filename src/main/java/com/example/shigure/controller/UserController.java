package com.example.shigure.controller;

import com.example.shigure.entity.User;
import com.example.shigure.exception.UserExistsException;
import com.example.shigure.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * UserController
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) throws UserExistsException {
        return userService.addUser(user);
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User addUserByForm(User user) throws UserExistsException {
        return userService.addUser(user);
    }
}