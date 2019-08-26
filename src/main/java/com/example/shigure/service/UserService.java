package com.example.shigure.service;

import com.example.shigure.entity.User;
import com.example.shigure.exception.UserExistsException;
import com.example.shigure.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * UserService
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) throws UserExistsException {
        if (existsUser(user)) {
            throw new UserExistsException("Username:" + user.getUsername() + "is already existed.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User insertUser = userRepository.insertUser(user);
        insertUser.setPassword(null);
        return insertUser;
    }

    private boolean existsUser(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }
}