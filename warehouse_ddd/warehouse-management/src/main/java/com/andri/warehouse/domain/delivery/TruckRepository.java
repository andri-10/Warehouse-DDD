package com.andri.warehouse.domain.delivery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TruckRepository {

    void save(Truck truck);
    void update(Truck truck);
    void delete(TruckId truckId);

    Optional<Truck> findById(TruckId truckId);
    List<Truck> findAll();
    List<Truck> findAvailableOn(LocalDate date);

    boolean existsByChassisNumber(ChassisNumber chassisNumber);
    boolean existsByLicensePlate(LicensePlate licensePlate);
}
