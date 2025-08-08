package com.andri.warehouse.interfaces.rest.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 1, message = "Role cannot be empty")
    private String role;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String email, String role) {
        this.email = email;
        this.role = role;
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

    public boolean hasEmail() {
        return email != null && !email.trim().isEmpty();
    }

    public boolean hasRole() {
        return role != null && !role.trim().isEmpty();
    }
}