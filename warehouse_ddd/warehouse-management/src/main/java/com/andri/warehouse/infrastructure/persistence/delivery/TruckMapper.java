package com.andri.warehouse.infrastructure.persistence.delivery;

import com.andri.warehouse.domain.delivery.*;
import java.math.BigDecimal;

public class TruckMapper {

    public static TruckJpaEntity toJpaEntity(Truck truck) {
        return new TruckJpaEntity(
                truck.getId().getTruckId(),
                truck.getChassisNumber().getChassisNumber(),
                truck.getLicensePlate().getLicensePlate(),
                truck.getContainerVolume().getCubicMeters(),
                truck.getReservedDate()
        );
    }

    public static Truck toDomain(TruckJpaEntity entity) {
        return Truck.reconstruct(
                TruckId.of(entity.getId()),
                ChassisNumber.of(entity.getChassisNumber()),
                LicensePlate.of(entity.getLicensePlate()),
                new ContainerVolume(entity.getContainerVolume())
        );
    }
}