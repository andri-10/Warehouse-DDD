package com.andri.warehouse.application.user.command;

import com.andri.warehouse.domain.user.UserId;

public class DeleteUserCommand {

    private final UserId userId;

    public DeleteUserCommand(UserId userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
