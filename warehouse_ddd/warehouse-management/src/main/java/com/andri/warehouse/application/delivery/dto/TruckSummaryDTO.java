package com.andri.warehouse.application.delivery.dto;

import java.math.BigDecimal;

public class TruckSummaryDTO {
    private final String id;
    private final String licensePlate;
    private final BigDecimal containerVolume;
    private final boolean available;

    public TruckSummaryDTO(String id, String licensePlate, BigDecimal containerVolume, boolean available) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public BigDecimal getContainerVolume() {
        return containerVolume;
    }

    public boolean isAvailable() {
        return available;
    }
}