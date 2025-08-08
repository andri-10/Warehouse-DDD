package com.andri.warehouse.interfaces.rest.dto.order.response;

import com.andri.warehouse.application.order.dto.OrderSummaryDTO;

public class OrderSummaryResponse {
    private String orderId;
    private String status;
    private String customerId;
    private String deadlineDate;
    private String submittedDate;
    private int itemCount;

    public OrderSummaryResponse() {

    }

    public OrderSummaryResponse(String orderId, String status, String customerId, String deadlineDate, String submittedDate, int itemCount) {
        setOrderId(orderId);
        setCustomerId(customerId);
        setStatus(status);
        setDeadlineDate(deadlineDate);
        setSubmittedDate(submittedDate);
        setItemCount(itemCount);
    }

    public static OrderSummaryResponse from(OrderSummaryDTO dto){
        return new OrderSummaryResponse(
                dto.getOrderId(),
                dto.getStatus(),
                dto.getCustomerId(),
                dto.getDeadlineDate().toString(),
                dto.getSubmittedDate() != null ? dto.getSubmittedDate().toString() : null,
                dto.getItemCount()

        );
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
