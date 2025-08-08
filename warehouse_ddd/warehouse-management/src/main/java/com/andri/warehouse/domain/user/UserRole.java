package com.andri.warehouse.domain.user;

public class UserRole {

    public enum Role {
        CLIENT,
        WAREHOUSE_MANAGER,
        SYSTEM_ADMIN
    }

    private final Role role;

    public UserRole(Role role) {
        if(role == null){
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }

    public UserRole(String roleString){
        if (roleString == null || roleString.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        try {
            this.role = UserRole.Role.valueOf(roleString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user role: " + roleString);
        }
    }

    public Role getRole() {
        return role;
    }

    public static UserRole client(){
        return new UserRole(UserRole.Role.CLIENT);
    }
    public static UserRole warehouseManager(){
        return new UserRole(UserRole.Role.WAREHOUSE_MANAGER);
    }
    public static UserRole systemAdmin(){
        return new UserRole(UserRole.Role.SYSTEM_ADMIN);
    }

    public boolean isClient() {
        return role == Role.CLIENT;
    }

    public boolean isWarehouseManager() {
        return role == Role.WAREHOUSE_MANAGER;
    }

    public boolean isSystemAdmin() {
        return role == Role.SYSTEM_ADMIN;
    }

    public boolean canManageOrders() {
        return role == Role.WAREHOUSE_MANAGER || role == Role.SYSTEM_ADMIN;
    }

    public boolean canManageUsers() {
        return role == Role.SYSTEM_ADMIN;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserRole other)) return false;
        return role == other.role;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                '}';
    }

}
