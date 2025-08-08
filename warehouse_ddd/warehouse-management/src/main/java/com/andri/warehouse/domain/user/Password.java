package com.andri.warehouse.domain.user;

import java.util.Objects;
import org.mindrot.jbcrypt.BCrypt;

public class Password {

    private static final int MIN_LENGTH = 8;
    private final String hashedPassword;

    private Password(String rawPassword) {
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (rawPassword.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("Password must be at least " + MIN_LENGTH + " characters long");
        }
        this.hashedPassword = hash(rawPassword);
    }

    private Password(String hashedPassword, boolean isAlreadyHashed) {
        if (isAlreadyHashed) {
            this.hashedPassword = hashedPassword;
        } else {
            this.hashedPassword = hash(hashedPassword);
        }
    }

    public static Password fromHash(String hashedPassword) {
        return new Password(hashedPassword, true);
    }

    private String hash(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean matches(String rawPassword) {
        return BCrypt.checkpw(rawPassword, this.hashedPassword);
    }

    public static Password of(String rawPassword) {
        return new Password(rawPassword);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Password other)) return false;
        return Objects.equals(this.hashedPassword, other.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedPassword);
    }

    @Override
    public String toString() {
        return "Password{******}";
    }
}
