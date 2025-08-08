package com.andri.warehouse.interfaces.rest.dto.user.response;

import com.andri.warehouse.domain.user.User;

public class LoginResponse {

    private String token;
    private String userId;
    private String username;
    private String email;
    private String role;
    private long expiresIn;

    public LoginResponse() {
    }

    public LoginResponse(String token, String userId, String username, String email, String role, long expiresIn) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.expiresIn = expiresIn;
    }

    public static LoginResponse from(User user, String token, long expiresIn) {
        return new LoginResponse(
                token,
                user.getUserId().getUserId(),
                user.getUsername().getUsername(),
                user.getEmail().getEmail(),
                user.getRole().toString(),
                expiresIn
        );
    }

    public static LoginResponse from(User user, String token) {
        return from(user, token, 86400000L);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}