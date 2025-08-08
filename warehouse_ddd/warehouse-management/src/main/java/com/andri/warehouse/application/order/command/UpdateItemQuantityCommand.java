package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class UpdateItemQuantityCommand {
    private final OrderId orderId;
    private final String itemId;
    private final int quantity;

    public UpdateItemQuantityCommand(OrderId orderId, String itemId, int quantity) {
        if(orderId == null){
            throw new IllegalArgumentException("orderId cannot be null");
        }else if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("itemId cannot be null or empty");
        }else if(quantity < 0 ){
            throw new IllegalArgumentException("quantity cannot be less than 0");
        }
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
