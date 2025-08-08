package com.andri.warehouse.domain.order;

import com.andri.warehouse.domain.user.UserId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private final OrderId id;

    private OrderStatus status;

    private LocalDateTime submittedDate;

    private LocalDateTime deadlineDate;

    private UserId customerId;

    private List<OrderItem> items;

    private Order(UserId customerId, LocalDateTime deadlineDate){
        if(customerId == null){
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if(deadlineDate.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Deadline date cannot be before current date");
        }

        this.id = OrderId.generate();
        this.status = OrderStatus.created();
        this.customerId = customerId;
        this.deadlineDate = deadlineDate;
        items = new ArrayList<>();

    }

    private Order(OrderId id, OrderStatus status, UserId customerId,
          LocalDateTime deadlineDate, LocalDateTime submittedDate,
          List<OrderItem> items) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.deadlineDate = deadlineDate;
        this.submittedDate = submittedDate;
        this.items = new ArrayList<>(items);
    }

    public static Order reconstruct(OrderId id, OrderStatus status, UserId customerId,
                                LocalDateTime deadlineDate, LocalDateTime submittedDate,
                                List<OrderItem> items) {
        return new Order(id, status, customerId, deadlineDate, submittedDate, items);
    }

    public static Order createOrder(UserId customerId, LocalDateTime deadlineDate){
        Order newOrder = new Order(customerId, deadlineDate);
        return newOrder;
    }

    public void submit(){
        if(items == null || items.isEmpty()){
            throw new IllegalStateException("Items cannot be null or empty");
        }

        this.status = OrderStatus.awaitingApproval();
        this.submittedDate = LocalDateTime.now();
    }

    public void addItem(String itemId, int quantity){
        ensureOrderIsModifiable();
        OrderItem item = new OrderItem(itemId, quantity);
        items.add(item);
    }

    public void removeItem(String itemId){
        ensureOrderIsModifiable();
        if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("ItemId cannot be null or empty");
        }
        items.removeIf(item -> item.getItemId().equals(itemId));
    }

    public void updateItemQuantity(String itemId, int quantity){
        ensureOrderIsModifiable();
        if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("ItemId cannot be null or empty");
        }else if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        boolean itemFound = false;
        for(OrderItem item : items){
            if(item.getItemId().equals(itemId)){
                item.requestedQuantity = quantity;
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            throw new IllegalArgumentException("Item not found in order: " + itemId);
        }
    }

    public void approve(){
        if(!status.isApprovable()){
            throw new IllegalStateException("Order is not approvable");
        }
        this.status = OrderStatus.approved();
    }

    public void decline(){
        if(!status.isApprovable()){
            throw new IllegalStateException("Order is not declinable");
        }
        this.status = OrderStatus.declined();
    }

    public void cancel(){
        if(!status.isCancellable()){
            throw new IllegalStateException("Order is not cancellable");
        }
        this.status = OrderStatus.canceled();
    }

    private void ensureOrderIsModifiable() {
        if (!status.isModifiable()) {
            throw new IllegalStateException("Order cannot be modified in status: " + status);
        }
    }

    public OrderId getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public UserId getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    public static class OrderItem {
        private final String itemId;
        private int requestedQuantity;

        public OrderItem(String itemId, int requestedQuantity) {
            if(itemId == null || itemId.trim().isEmpty()){
                throw new NullPointerException("Item cannot be null or empty");
            }else if(requestedQuantity < 0){
                throw new IllegalArgumentException("Requested quantity cannot be negative");
            }
            this.itemId = itemId;
            this.requestedQuantity = requestedQuantity;
        }

        public String getItemId() {
            return itemId;
        }

        public int getRequestedQuantity() {
            return requestedQuantity;
        }
    }
}
