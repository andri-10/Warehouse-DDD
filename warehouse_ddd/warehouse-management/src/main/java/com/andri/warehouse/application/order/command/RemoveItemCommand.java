package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.order.OrderId;

public class RemoveItemCommand {

    private final OrderId orderId;
    private final String itemId;

    public RemoveItemCommand(OrderId orderId, String itemId) {
        if(orderId == null){
            throw new IllegalArgumentException("orderId cannot be null");
        }else if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("itemId cannot be null or empty");
        }
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getItemId() {
        return itemId;
    }


}
