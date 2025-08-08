package com.andri.warehouse.application.user.command;

public class CreateUserCommand {
    private final String username;
    private final String email;
    private final String password;
    private final String role;


    public CreateUserCommand(String username, String email, String password, String role) {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("username cannot be null or empty");
        }else if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("email cannot be null or empty");
        }else if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("password cannot be null or empty");
        }else if(role == null || role.trim().isEmpty()){
            throw new IllegalArgumentException("role cannot be null or empty");
        }
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
