package com.andri.warehouse.domain.delivery;

import java.util.Objects;
import java.util.regex.Pattern;

public class LicensePlate {

    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Z]{1,3}[- ]?[0-9]{1,4}$");
    private final String licensePlate;

    private LicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }

        String normalized = licensePlate.trim().toUpperCase();

        if (!VALID_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("Invalid license plate format: " + licensePlate);
        }

        this.licensePlate = normalized;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public static LicensePlate of(String licensePlate) {
        return new LicensePlate(licensePlate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicensePlate)) return false;
        LicensePlate that = (LicensePlate) o;
        return licensePlate.equals(that.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }

    @Override
    public String toString() {
        return "LicensePlate{" +
                "licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
