package com.andri.warehouse.application.order.command;

import com.andri.warehouse.domain.user.UserId;

import java.time.LocalDateTime;

public class CreateOrderCommand {
    private final UserId customerId;
    private final LocalDateTime deadlineDate;

    public CreateOrderCommand(UserId customerId, LocalDateTime deadlineDate) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (deadlineDate == null) {
            throw new IllegalArgumentException("Deadline date cannot be null");
        }
        if (deadlineDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline date cannot be in the past");
        }

        this.customerId = customerId;
        this.deadlineDate = deadlineDate;
    }

    public UserId getCustomerId() {
        return customerId;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }
}

