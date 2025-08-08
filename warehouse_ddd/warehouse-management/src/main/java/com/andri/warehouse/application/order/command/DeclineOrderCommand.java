package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class DeclineOrderCommand {
    private final OrderId orderId;
    private final String reason;

    public DeclineOrderCommand(OrderId orderId, String reason) {
        if(orderId == null)
            throw new IllegalArgumentException("OrderId cannot be null");

        this.orderId = orderId;
        this.reason = reason;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

}
