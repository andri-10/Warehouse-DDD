package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class CancelOrderCommand {
    private final OrderId orderId;

    public CancelOrderCommand(OrderId orderId) {
        if(orderId == null){
            throw new IllegalArgumentException("orderId is null");
        }
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
