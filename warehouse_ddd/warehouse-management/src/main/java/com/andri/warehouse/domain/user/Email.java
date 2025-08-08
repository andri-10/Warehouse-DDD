package com.andri.warehouse.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private final String email;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE
    );

    private Email(String email) {
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }else if(!EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.email = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Email)) return false;
        Email other = (Email) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }

}
