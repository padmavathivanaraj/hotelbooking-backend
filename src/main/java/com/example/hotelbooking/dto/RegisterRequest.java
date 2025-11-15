package com.example.hotelbooking.dto;

import java.util.HashSet;
import java.util.Set;

public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public RegisterRequest() {}

    public RegisterRequest(String username, String email, String password, Set<String> roles) {
        this.username = username != null ? username.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.password = password;
        if (roles != null) {
            this.roles = roles;
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username != null ? username.trim() : null;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email != null ? email.trim() : null;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        if (roles != null) this.roles = roles;
    }
}
