package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class SubmitOrderCommand {
    private final OrderId orderId;

    public SubmitOrderCommand(OrderId orderId) {
        if(orderId == null)
            throw new IllegalArgumentException("orderId cannot be null");
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
