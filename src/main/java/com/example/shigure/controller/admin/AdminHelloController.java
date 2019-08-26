package com.example.shigure.controller.admin;

import com.example.shigure.security.LoginUser;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminHelloController
 */
@RestController
@RequestMapping("/admin/api")
public class AdminHelloController {

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal LoginUser user) {
        return "Hello " + user.getUsername();
    }
}