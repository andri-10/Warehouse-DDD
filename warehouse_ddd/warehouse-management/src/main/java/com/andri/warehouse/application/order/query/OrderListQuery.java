package com.andri.warehouse.application.order.query;

public class OrderListQuery {
    private final String customerId;
    private final String status;

    public OrderListQuery(String customerId, String status) {
        this.customerId = customerId;
        this.status = status;
    }

    public boolean hasCustomerId() {
        return customerId != null && !customerId.trim().isEmpty();
    }

    public boolean hasStatus(){
        return status != null && !status.trim().isEmpty();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }
}
