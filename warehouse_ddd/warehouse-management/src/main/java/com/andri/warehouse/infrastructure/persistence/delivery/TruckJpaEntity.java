package com.andri.warehouse.infrastructure.persistence.delivery;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "trucks")
public class TruckJpaEntity {

    @Id
    private String id;

    @Column(name = "chassis_number", unique = true, nullable = false)
    private String chassisNumber;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "container_volume", nullable = false, precision = 10, scale = 2)
    private BigDecimal containerVolume;

    @Column(name = "reserved_date")
    private LocalDate reservedDate;

    public TruckJpaEntity() {}

    public TruckJpaEntity(String id, String chassisNumber, String licensePlate,
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

    public void setId(String id) {
        this.id = id;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getContainerVolume() {
        return containerVolume;
    }

    public void setContainerVolume(BigDecimal containerVolume) {
        this.containerVolume = containerVolume;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }
}