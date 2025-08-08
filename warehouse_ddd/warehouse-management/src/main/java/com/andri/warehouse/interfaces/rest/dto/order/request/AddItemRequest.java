package com.andri.warehouse.interfaces.rest.dto.order.request;

public class AddItemRequest {
    private String itemId;
    private int quantity;

    public AddItemRequest(){

    }

    public AddItemRequest(String itemId, int quantity) {
        setItemId(itemId);
        setQuantity(quantity);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("ItemId cannot be null or empty");
        }
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }
        this.quantity = quantity;
    }
}


