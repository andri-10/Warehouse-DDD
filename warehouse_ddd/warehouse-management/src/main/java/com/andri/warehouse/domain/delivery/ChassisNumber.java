package com.andri.warehouse.domain.delivery;

import java.util.Objects;
import java.util.regex.Pattern;

public class ChassisNumber {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");
    private final String chassisNumber;

    private ChassisNumber(String chassisNumber) {
        if (chassisNumber == null || chassisNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Chassis number cannot be null or empty");
        }
        if (!VALID_PATTERN.matcher(chassisNumber).matches()) {
            throw new IllegalArgumentException("Invalid chassis number format: must be 17 characters, " +
                    "uppercase letters (A-Z) and digits (0-9), excluding I, O, Q");
        }
        this.chassisNumber = chassisNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public static ChassisNumber of(String chassisNumber) {
        return new ChassisNumber(chassisNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChassisNumber)) return false;
        ChassisNumber that = (ChassisNumber) o;
        return chassisNumber.equals(that.chassisNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chassisNumber);
    }

    @Override
    public String toString() {
        return "ChassisNumber{" +
                "chassisNumber='" + chassisNumber + '\'' +
                '}';
    }
}
