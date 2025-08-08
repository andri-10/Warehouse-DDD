package com.andri.warehouse.application.user.exception;

import com.andri.warehouse.domain.user.UserId;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserId userId) {
        super("User not found: " + userId.getUserId());
    }

    public UserNotFoundException(String identifier) {
        super("User not found: " + identifier);
    }

}