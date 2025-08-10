package com.andri.warehouse.application.delivery.command;

public class DeleteTruckCommand {
    private final String truckId;

    public DeleteTruckCommand(String truckId) {
        this.truckId = truckId;
    }

    public String getTruckId() {
        return truckId;
    }
}
