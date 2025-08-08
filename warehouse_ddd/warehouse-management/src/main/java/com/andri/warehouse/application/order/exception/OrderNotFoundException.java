package com.andri.warehouse.application.order.exception;

import com.andri.warehouse.domain.order.OrderId;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(OrderId orderId) {
        super("Order not found: " + orderId.getOrderId());
    }
}
