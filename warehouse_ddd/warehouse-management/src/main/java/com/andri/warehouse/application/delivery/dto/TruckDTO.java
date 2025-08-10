package com.andri.warehouse.application.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TruckDTO {
    private final String id;
    private final String chassisNumber;
    private final String licensePlate;
    private final BigDecimal containerVolume;
    private final LocalDate reservedDate;

    public TruckDTO(String id, String chassisNumber, String licensePlate,
                    BigDecimal containerVolume, LocalDate reservedDate) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
        this.reservedDate = reservedDate;
    }

    public String getId() {
        return id;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public BigDecimal getContainerVolume() {
        return containerVolume;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }
}
