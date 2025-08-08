package com.andri.warehouse.interfaces.rest.dto.order.request;

public class DeclineOrderRequest {

    private String reason;

    public DeclineOrderRequest() {}

    public DeclineOrderRequest(String reason) {
        setReason(reason);
    }

       public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
