package com.andri.warehouse.interfaces.rest.dto.order.response;

public class CreateOrderResponse {
    private String orderId;


    public CreateOrderResponse() {
    }

    public CreateOrderResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}