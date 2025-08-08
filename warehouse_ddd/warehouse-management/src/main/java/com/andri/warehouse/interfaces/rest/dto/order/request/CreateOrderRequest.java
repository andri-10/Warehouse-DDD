package com.andri.warehouse.interfaces.rest.dto.order.request;

public class CreateOrderRequest {
    private String customerId;
    private String deadlineDate;


    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String customerId, String deadlineDate) {
        setCustomerId(customerId);
        setDeadlineDate(deadlineDate);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("customerId cannot be null or empty");
        }
        this.customerId = customerId;
    }

    public void setDeadlineDate(String deadlineDate) {
        if (deadlineDate == null || deadlineDate.trim().isEmpty()) {
            throw new IllegalArgumentException("deadlineDate cannot be null or empty");
        }
        this.deadlineDate = deadlineDate;
    }
}
