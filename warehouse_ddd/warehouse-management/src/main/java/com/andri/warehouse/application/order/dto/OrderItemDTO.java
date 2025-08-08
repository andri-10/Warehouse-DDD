package com.andri.warehouse.application.order.dto;

import com.andri.warehouse.domain.order.Order;


public class OrderItemDTO {

    private String itemId;
    private int requestedQuantity;

    private OrderItemDTO(String itemId, int requestedQuantity) {
        setItemId(itemId);
        setRequestedQuantity(requestedQuantity);
    }

    public static OrderItemDTO from(Order.OrderItem item){
        return new OrderItemDTO(item.getItemId(), item.getRequestedQuantity());
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
