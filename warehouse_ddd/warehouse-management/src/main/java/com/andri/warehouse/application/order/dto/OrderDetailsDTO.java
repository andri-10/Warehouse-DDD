package com.andri.warehouse.application.order.dto;

import com.andri.warehouse.domain.order.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailsDTO {

    private String orderId;
    private String status;
    private String customerId;
    private LocalDateTime deadlineDate;
    private LocalDateTime submittedDate;
    private List<OrderItemDTO> items;

    private OrderDetailsDTO(String orderId, String status, String customerId, LocalDateTime deadlineDate, LocalDateTime submittedDate, List<OrderItemDTO> items) {
        setOrderId(orderId);
        setStatus(status);
        setCustomerId(customerId);
        setDeadlineDate(deadlineDate);
        setSubmittedDate(submittedDate);
        setItems(items);
    }

    public static OrderDetailsDTO from(Order order) {

        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(OrderItemDTO::from)
                .collect(Collectors.toList());

        return new OrderDetailsDTO(
                order.getId().getOrderId(),
                order.getStatus().getStatus().toString(),
                order.getCustomerId().getUserId(),
                order.getDeadlineDate(),
                order.getSubmittedDate(),
                itemDTOs
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }




}
