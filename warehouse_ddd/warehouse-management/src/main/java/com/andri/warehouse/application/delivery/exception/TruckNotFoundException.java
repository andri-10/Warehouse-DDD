package com.andri.warehouse.application.delivery.exception;

import com.andri.warehouse.domain.delivery.TruckId;

public class TruckNotFoundException extends RuntimeException {

    public TruckNotFoundException(TruckId truckId) {
        super("Truck not found: " + truckId.getTruckId());
    }
}