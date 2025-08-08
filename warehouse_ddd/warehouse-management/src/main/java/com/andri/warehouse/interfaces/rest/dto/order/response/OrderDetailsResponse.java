package com.andri.warehouse.interfaces.rest.dto.order.response;

import com.andri.warehouse.application.order.dto.OrderDetailsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailsResponse {
    private String orderId;
    private String orderNumber;
    private String status;
    private String customerId;
    private String deadlineDate;
    private String submittedDate;
    private List<OrderItemResponse> items;

    public OrderDetailsResponse(){

    }

    public OrderDetailsResponse(String orderId, String orderNumber, String status, String customerId, String deadlineDate, String submittedDate, List <OrderItemResponse> items) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.status = status;
        this.customerId = customerId;
        this.deadlineDate = deadlineDate;
        this.submittedDate = submittedDate;
        this.items = items;
    }

    public static OrderDetailsResponse from(OrderDetailsDTO dto) {
        List<OrderItemResponse> itemResponses = dto.getItems().stream()
                .map(OrderItemResponse::from)
                .collect(Collectors.toList());

        return new OrderDetailsResponse(
                dto.getOrderId(),
                dto.getOrderId(),
                dto.getStatus(),
                dto.getCustomerId(),
                dto.getDeadlineDate().toString(),
                dto.getSubmittedDate() != null ? dto.getSubmittedDate().toString() : null,
                itemResponses
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}
