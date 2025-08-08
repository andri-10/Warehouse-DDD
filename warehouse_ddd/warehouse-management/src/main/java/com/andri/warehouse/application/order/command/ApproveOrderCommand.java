package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class ApproveOrderCommand {
    private final OrderId orderId;

    public ApproveOrderCommand(OrderId orderId) {
        if(orderId == null)
            throw new IllegalArgumentException("OrderId cannot be null");

        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
