package com.andri.warehouse.domain.order;

public class OrderStatus {

    public enum Status {
        CREATED,
        AWAITING_APPROVAL,
        APPROVED,
        DECLINED,
        UNDER_DELIVERY,
        FULFILLED,
        CANCELED
    }

    private final Status status;

    public OrderStatus(Status status) {
        if (status == null)
            throw new IllegalArgumentException("status is null");
        this.status = status;
    }

    public OrderStatus(String statusString) {
        if (statusString == null || statusString.trim().isEmpty()) {
            throw new IllegalArgumentException("Status String cannot be null or empty");
        }
        try {
            this.status = Status.valueOf(statusString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + statusString);
        }
    }

    //Factory Methods
    public static OrderStatus created() {
        return new OrderStatus(Status.CREATED);
    }

    public static OrderStatus awaitingApproval() {
        return new OrderStatus(Status.AWAITING_APPROVAL);
    }

    public static OrderStatus approved() {
        return new OrderStatus(Status.APPROVED);
    }

    public static OrderStatus declined() {
        return new OrderStatus(Status.DECLINED);
    }

    public static OrderStatus underDelivery() {
        return new OrderStatus(Status.UNDER_DELIVERY);
    }

    public static OrderStatus fulfilled() {
        return new OrderStatus(Status.FULFILLED);
    }

    public static OrderStatus canceled() {
        return new OrderStatus(Status.CANCELED);
    }

    public Status getStatus() {
        return status;
    }

    //Business Methods
    public boolean isModifiable() {
        return status == Status.CREATED || status == Status.DECLINED;
    }

    public boolean isApprovable() {
        return status == Status.AWAITING_APPROVAL;
    }

    public boolean isCancellable() {
        return status != Status.FULFILLED;
    }

    public boolean isDeliverable() {
        return status == Status.APPROVED;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderStatus that = (OrderStatus) obj;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    @Override
    public String toString() {
        return "OrderStatus{" + status + "}";
    }

}
