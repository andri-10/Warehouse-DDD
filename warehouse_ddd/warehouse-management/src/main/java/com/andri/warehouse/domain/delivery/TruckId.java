package com.andri.warehouse.domain.delivery;

import com.andri.warehouse.domain.user.UserId;

import java.util.Objects;
import java.util.UUID;

public class TruckId {
    private final String truckId;

    private TruckId(String truckId) {
        if(truckId == null || truckId.trim().isEmpty()) {
            throw new IllegalArgumentException("truckId cannot be null or empty");
        }
        this.truckId = truckId;
    }

    public String getTruckId() {
        return truckId;
    }

    public static TruckId generate(){
        String truckId = UUID.randomUUID().toString();
        return new TruckId(truckId);
    }

    public static TruckId of(String truckId){
        return new TruckId(truckId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TruckId truckId1)) return false;
        return Objects.equals(truckId, truckId1.truckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckId);
    }

    @Override
    public String toString() {
        return "TruckId{" +
                "truckId='" + truckId + '\'' +
                '}';
    }

}
