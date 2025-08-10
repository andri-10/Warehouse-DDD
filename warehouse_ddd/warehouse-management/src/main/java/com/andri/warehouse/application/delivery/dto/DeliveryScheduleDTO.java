package com.andri.warehouse.application.delivery.dto;

import java.time.LocalDate;
import java.util.List;

public class DeliveryScheduleDTO {
    private final String orderId;
    private final LocalDate deliveryDate;
    private final List<TruckSummaryDTO> assignedTrucks;

    public DeliveryScheduleDTO(String orderId, LocalDate deliveryDate, List<TruckSummaryDTO> assignedTrucks) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.assignedTrucks = assignedTrucks;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<TruckSummaryDTO> getAssignedTrucks() {
        return assignedTrucks;
    }
}
