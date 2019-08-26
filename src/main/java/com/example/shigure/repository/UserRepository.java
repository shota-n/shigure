package com.example.shigure.repository;

import java.util.Optional;

import com.example.shigure.entity.User;
import com.example.shigure.jooq.tables.daos.UserDao;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

/**
 * UserRepository
 */
@Repository
@AllArgsConstructor
public class UserRepository {

    private UserDao userDao;

    public Optional<User> findByUsername(String username) {
        return userDao.fetchByUsername(username).stream()
        .findFirst()
        .map(userJooq -> {
            return User.builder()
            .username(userJooq.getUsername())
            .password(userJooq.getPassword())
            .isAdmin(userJooq.getAdminFlag().byteValue() == 1)
            .build();
        });

    }

    public User insertUser(User user) {
        com.example.shigure.jooq.tables.pojos.User userJooq = new com.example.shigure.jooq.tables.pojos.User();
        userJooq.setUsername(user.getUsername());
        userJooq.setPassword(user.getPassword());
        userJooq.setAdminFlag(user.isAdmin() ? (byte) 1 : 0);
        userDao.insert(userJooq);
        return user;
    }
}