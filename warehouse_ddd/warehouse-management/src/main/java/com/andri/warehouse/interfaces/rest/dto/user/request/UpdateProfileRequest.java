package com.andri.warehouse.interfaces.rest.dto.user.request;

import jakarta.validation.constraints.Email;

public class UpdateProfileRequest {

    @Email(message = "Email must be valid")
    private String email;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasEmail() {
        return email != null && !email.trim().isEmpty();
    }
}