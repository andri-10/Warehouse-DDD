package com.andri.warehouse.interfaces.rest.dto.order.response;

import com.andri.warehouse.application.order.dto.OrderItemDTO;

public class OrderItemResponse {
    private String itemId;
    private int requestedQuantity;

    public OrderItemResponse() {}


    private OrderItemResponse(String itemId, int requestedQuantity) {
        setItemId(itemId);
        setRequestedQuantity(requestedQuantity);
    }

    public static OrderItemResponse from(OrderItemDTO dto){
        return new OrderItemResponse(dto.getItemId(), dto.getRequestedQuantity());
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}