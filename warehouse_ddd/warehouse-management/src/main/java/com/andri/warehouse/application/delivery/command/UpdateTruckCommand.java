package com.andri.warehouse.application.delivery.command;

import java.math.BigDecimal;

public class UpdateTruckCommand {
    private final String truckId;
    private final String licensePlate;
    private final BigDecimal containerVolume;

    public UpdateTruckCommand(String truckId, String licensePlate, BigDecimal containerVolume) {
        this.truckId = truckId;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public BigDecimal getContainerVolume() {
        return containerVolume;
    }
}
