package com.andri.warehouse.domain.order;

import java.util.Objects;
import java.util.UUID;

public class OrderId {

    private final String orderId;

    private OrderId(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order id: " + orderId);
        }
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public static OrderId generate() {
        String newOrderId = UUID.randomUUID().toString();
        return new OrderId(newOrderId);
    }

    public static OrderId of(String id){
        return new OrderId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderId that = (OrderId) obj;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }

    @Override
    public String toString() {
        return "OrderId [" + orderId + "]";
    }

}
