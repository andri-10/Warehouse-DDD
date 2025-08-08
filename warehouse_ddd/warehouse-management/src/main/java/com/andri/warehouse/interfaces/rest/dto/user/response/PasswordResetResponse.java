package com.andri.warehouse.interfaces.rest.dto.user.response;

public class PasswordResetResponse {

    private String message;

    public PasswordResetResponse() {
    }

    public PasswordResetResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static PasswordResetResponse success() {
        return new PasswordResetResponse("Password reset email sent successfully. Please check your email.");
    }

    public static PasswordResetResponse emailNotFound() {
        return new PasswordResetResponse("If an account with that email exists, a password reset email has been sent.");
    }

    public static PasswordResetResponse resetComplete() {
        return new PasswordResetResponse("Password has been reset successfully. You can now login with your new password.");
    }
}