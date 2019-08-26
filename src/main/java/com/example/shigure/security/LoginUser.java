package com.example.shigure.security;

import com.example.shigure.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * LoginUser
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private User user;

    public LoginUser(User user) {
        super(user.getUsername(), user.getPassword(), determineRoles(user.isAdmin()));
        this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLE = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLE = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? ADMIN_ROLE : USER_ROLE;
    }

    public User getUser() {
        return user;
    }
}