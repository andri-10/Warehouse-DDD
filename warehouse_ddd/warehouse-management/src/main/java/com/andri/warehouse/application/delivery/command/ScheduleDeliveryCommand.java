package com.andri.warehouse.application.delivery.command;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDeliveryCommand {
    private final String orderId;
    private final LocalDate deliveryDate;
    private final List<String> truckIds;

    public ScheduleDeliveryCommand(String orderId, LocalDate deliveryDate, List<String> truckIds) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.truckIds = truckIds;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<String> getTruckIds() {
        return truckIds;
    }
}
