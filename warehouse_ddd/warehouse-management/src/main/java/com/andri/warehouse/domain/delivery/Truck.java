package com.andri.warehouse.domain.delivery;

import java.time.LocalDate;
import java.util.Objects;

public class Truck {

    private final TruckId id;
    private final ChassisNumber chassisNumber;
    private LicensePlate licensePlate;
    private ContainerVolume containerVolume;
    private LocalDate reservedDate;

    private Truck(ChassisNumber chassisNumber, LicensePlate licensePlate, ContainerVolume containerVolume) {
        if (chassisNumber == null || licensePlate == null || containerVolume == null) {
            throw new IllegalArgumentException("Chassis Number, License Plate, and Container Volume are required");
        }
        this.id = TruckId.generate();
        this.chassisNumber = chassisNumber;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
    }

    private Truck(TruckId id, ChassisNumber chassisNumber, LicensePlate licensePlate, ContainerVolume containerVolume) {
        if (id == null || chassisNumber == null || licensePlate == null || containerVolume == null) {
            throw new IllegalArgumentException("Truck fields cannot be null");
        }
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.licensePlate = licensePlate;
        this.containerVolume = containerVolume;
    }

    public static Truck createTruck(ChassisNumber chassisNumber, LicensePlate licensePlate, ContainerVolume containerVolume) {
        return new Truck(chassisNumber, licensePlate, containerVolume);
    }

    public static Truck reconstruct(TruckId id, ChassisNumber chassisNumber, LicensePlate licensePlate, ContainerVolume containerVolume) {
        return new Truck(id, chassisNumber, licensePlate, containerVolume);
    }

    public boolean canCarry(ContainerVolume loadVolume) {
        return this.containerVolume.canFit(loadVolume);
    }

    public void reserveForDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Reservation date cannot be null");
        }
        if (isWeekend(date)) {
            throw new IllegalArgumentException("Cannot reserve truck on weekends");
        }
        if (!isAvailableOn(date)) {
            throw new IllegalStateException("Truck is already reserved for " + date);
        }
        this.reservedDate = date;
    }

    public boolean isAvailableOn(LocalDate date) {
        return reservedDate == null || !reservedDate.equals(date);
    }

    public void releaseReservation() {
        this.reservedDate = null;
    }

    public void updateLicensePlate(LicensePlate newPlate) {
        if (newPlate == null) throw new IllegalArgumentException("License plate cannot be null");
        this.licensePlate = newPlate;
    }

    public void updateContainerVolume(ContainerVolume newVolume) {
        if (newVolume == null) throw new IllegalArgumentException("Container volume cannot be null");
        this.containerVolume = newVolume;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    public TruckId getId() {
        return id;
    }

    public ChassisNumber getChassisNumber() {
        return chassisNumber;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public ContainerVolume getContainerVolume() {
        return containerVolume;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;
        Truck truck = (Truck) o;
        return id.equals(truck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
