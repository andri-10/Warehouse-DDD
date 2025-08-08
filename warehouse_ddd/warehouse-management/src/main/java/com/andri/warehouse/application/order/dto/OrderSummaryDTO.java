package com.andri.warehouse.application.order.dto;

import com.andri.warehouse.domain.order.Order;

import java.time.LocalDateTime;

public class OrderSummaryDTO {
    private String orderId;
    private String status;
    private String customerId;
    private LocalDateTime deadlineDate;
    private LocalDateTime submittedDate;
    private int itemCount;

        public OrderSummaryDTO(String orderId, String status, String customerId,
                           LocalDateTime deadlineDate, LocalDateTime submittedDate, int itemCount) {
        this.orderId = orderId;
        this.status = status;
        this.customerId = customerId;
        this.deadlineDate = deadlineDate;
        this.submittedDate = submittedDate;
        this.itemCount = itemCount;
    }


    public static OrderSummaryDTO from(Order order) {
        return new OrderSummaryDTO(
                order.getId().getOrderId(),
                order.getStatus().getStatus().toString(),
                order.getCustomerId().getUserId(),
                order.getDeadlineDate(),
                order.getSubmittedDate(),
                order.getItems().size()
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }
}
