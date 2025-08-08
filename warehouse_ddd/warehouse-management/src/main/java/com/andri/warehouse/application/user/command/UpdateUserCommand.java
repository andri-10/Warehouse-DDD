package com.andri.warehouse.application.user.command;

import com.andri.warehouse.domain.user.UserId;

public class UpdateUserCommand {

    private final UserId userId;
    private final String email;
    private final String role;


    public UpdateUserCommand(UserId userId, String email, String role) {
        if(userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        this.userId = userId;
        this.email = (email != null) ? email.toLowerCase() : null;
        this.role = (role != null) ? role.toUpperCase() : null;
    }

    public boolean hasEmail(){
        return email != null;
    }

    public boolean hasRole(){
        return role != null;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
