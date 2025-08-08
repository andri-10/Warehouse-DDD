package com.andri.warehouse.application.user.command;

import com.andri.warehouse.domain.user.UserId;

public class ChangePasswordCommand {
    private final UserId userId;
    private final String currentPassword;
    private final String newPassword;

    public ChangePasswordCommand(UserId userId, String currentPassword, String newPassword) {
        if(userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }else if(currentPassword == null || currentPassword.trim().isEmpty()){
            throw new IllegalArgumentException("currentPassword cannot be null or empty");
        }else if(newPassword == null || newPassword.trim().isEmpty()){
            throw new IllegalArgumentException("newPassword cannot be null or empty");
        }
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }
}
