package com.andri.warehouse.domain.user;

import java.util.Objects;
import java.util.UUID;

public class UserId {

    private final String userId;

    private UserId(String userId) {
        if(userId == null || userId.trim().isEmpty()){
            throw new IllegalArgumentException("User Id should not be null or empty");
        }
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public static UserId generate() {
        String newUserId = UUID.randomUUID().toString();
        return new UserId(newUserId);
    }

    public static UserId of(String id){
        return new UserId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserId that = (UserId) obj;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "UserId [" + userId + "]";
    }


}
