package com.andri.warehouse.interfaces.rest.dto.order.request;


public class UpdateItemQuantityRequest {
    private int quantity;

    public UpdateItemQuantityRequest() {}

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = quantity;
    }
}
