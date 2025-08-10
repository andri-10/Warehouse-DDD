package com.andri.warehouse.domain.delivery;

import java.math.BigDecimal;
import java.util.Objects;

public final class ContainerVolume {

    private final BigDecimal cubicMeters;

    public ContainerVolume(BigDecimal cubicMeters) {
        if (cubicMeters == null || cubicMeters.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Container volume must be non-negative");
        }
        this.cubicMeters = cubicMeters;
    }

    public static ContainerVolume ofCubicMeters(double cubicMeters) {
        return new ContainerVolume(BigDecimal.valueOf(cubicMeters));
    }

    public BigDecimal getCubicMeters() {
        return cubicMeters;
    }

    public ContainerVolume add(ContainerVolume other) {
        return new ContainerVolume(this.cubicMeters.add(other.cubicMeters));
    }

    public ContainerVolume subtract(ContainerVolume other) {
        BigDecimal result = this.cubicMeters.subtract(other.cubicMeters);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Resulting volume cannot be negative");
        }
        return new ContainerVolume(result);
    }

    public boolean canFit(ContainerVolume itemVolume) {
        return this.cubicMeters.compareTo(itemVolume.cubicMeters) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContainerVolume)) return false;
        ContainerVolume that = (ContainerVolume) o;
        return cubicMeters.compareTo(that.cubicMeters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubicMeters);
    }

    @Override
    public String toString() {
        return cubicMeters + " m³";
    }
}
