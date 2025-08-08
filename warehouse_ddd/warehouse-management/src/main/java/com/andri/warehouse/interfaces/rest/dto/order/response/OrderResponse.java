package com.andri.warehouse.interfaces.rest.dto.order.response;

import java.util.List;

public class OrderResponse {
    private String orderId;
    private String status;
    private String customerId;
    private String deadlineDate;
    private String submittedDate;
    private List<OrderItemResponse> items;

    public OrderResponse() {
    }

    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public String getCustomerId() { return customerId; }
    public String getDeadlineDate() { return deadlineDate; }
    public String getSubmittedDate() { return submittedDate; }
    public List<OrderItemResponse> getItems() { return items; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setStatus(String status) { this.status = status; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setDeadlineDate(String deadlineDate) { this.deadlineDate = deadlineDate; }
    public void setSubmittedDate(String submittedDate) { this.submittedDate = submittedDate; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }
}
