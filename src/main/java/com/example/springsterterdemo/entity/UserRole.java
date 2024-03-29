package com.example.springsterterdemo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER_ROLE, ADMIN_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
