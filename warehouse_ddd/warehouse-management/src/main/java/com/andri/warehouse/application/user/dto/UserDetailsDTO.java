package com.andri.warehouse.application.user.dto;


import com.andri.warehouse.domain.user.User;

public class UserDetailsDTO {
    private final String userId;
    private final String username;
    private final String email;
    private final String role;

    private UserDetailsDTO(String userId, String username, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static UserDetailsDTO from(User user) {
        return new UserDetailsDTO(
                user.getUserId().getUserId(),
                user.getUsername().getUsername(),
                user.getEmail().getEmail(),
                user.getRole().getRole().toString()
        );
    }


    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}