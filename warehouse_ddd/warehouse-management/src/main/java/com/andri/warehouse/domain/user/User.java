package com.andri.warehouse.domain.user;

public class User {

    private final UserId userId;

    private Username username;

    private Email email;

    private UserRole role;

    private Password password;

    private User(Username username, Email email, Password password, UserRole role) {
        if(username == null){
            throw new IllegalArgumentException("Username cannot be null");
        }else if(email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.userId = UserId.generate();
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    private User(UserId userId, Username username, Email email, UserRole role, Password password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public static User reconstruct(UserId userId, Username username, Email email, UserRole role, Password password){
        return new User(userId, username, email, role, password);
    }

    public static User createUser(Username username, Email email, Password password, UserRole role){
        return new User(username, email, password, role);
    }

    public void changePassword(String currentPassword, String newPassword){
        if(!this.password.matches(currentPassword)){
            throw new IllegalArgumentException("Current password is incorrect");
        }
        this.password = Password.of(newPassword);
    }

    public void updateEmail(Email newEmail){
        this.email = Email.of(newEmail.getEmail());
    }

    public void changeRole(UserRole newRole){
        this.role = newRole;
    }

    public boolean isClient(){
        return role.isClient();
    }

    public boolean isSystemAdmin(){
        return role.isSystemAdmin();
    }

    public boolean isWarehouseManager(){
        return role.isWarehouseManager();
    }

    public boolean canManageOrders(){
        return isWarehouseManager() || isSystemAdmin();
    }

    public boolean canManageUsers(){
        return isSystemAdmin();
    }

    public UserId getUserId() {
        return userId;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public Password getPassword() {
        return password;
    }
}
