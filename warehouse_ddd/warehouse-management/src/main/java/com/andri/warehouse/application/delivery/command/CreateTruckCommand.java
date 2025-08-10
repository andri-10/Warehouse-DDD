package com.andri.warehouse.application.delivery.command;

import java.math.BigDecimal;

public class CreateTruckCommand {
    private final String chassisNumber;
    private final String licensePlate;
    private final BigDecimal containerVolume;

    public CreateTruckCommand(String chassisNumber, String licensePlate, BigDecimal containerVolume) {
        this.chassisNumber = chassisNumber;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
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
}