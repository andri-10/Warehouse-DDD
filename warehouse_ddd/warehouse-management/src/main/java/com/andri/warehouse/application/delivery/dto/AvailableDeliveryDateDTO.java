package com.andri.warehouse.application.delivery.dto;

import java.time.LocalDate;
import java.util.List;

public class AvailableDeliveryDateDTO {
    private final LocalDate date;
    private final List<TruckSummaryDTO> availableTrucks;

    public AvailableDeliveryDateDTO(LocalDate date, List<TruckSummaryDTO> availableTrucks) {
        this.date = date;
        this.availableTrucks = availableTrucks;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<TruckSummaryDTO> getAvailableTrucks() {
        return availableTrucks;
    }
}