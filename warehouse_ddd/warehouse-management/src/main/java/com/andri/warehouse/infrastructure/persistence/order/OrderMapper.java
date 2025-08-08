package com.andri.warehouse.infrastructure.persistence.order;

import com.andri.warehouse.domain.order.Order;
import com.andri.warehouse.domain.order.OrderId;
import com.andri.warehouse.domain.order.OrderStatus;
import com.andri.warehouse.domain.user.UserId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderJpaEntity toJpaEntity(Order domainOrder) {

        OrderId orderId = domainOrder.getId();
        OrderStatus orderStatus = domainOrder.getStatus();
        LocalDateTime submittedDate = (domainOrder.getSubmittedDate() != null) ? domainOrder.getSubmittedDate() : null;
        LocalDateTime deadlineDate = domainOrder.getDeadlineDate();
        UserId customerId = domainOrder.getCustomerId();
        List<Order.OrderItem> items = domainOrder.getItems();

        OrderJpaEntity jpaEntity = new OrderJpaEntity();
        jpaEntity.setId(orderId.getOrderId());
        jpaEntity.setStatus(orderStatus.getStatus().toString());

        if(submittedDate != null) {
            jpaEntity.setSubmittedDate(submittedDate);
        }

        jpaEntity.setDeadlineDate(deadlineDate);
        jpaEntity.setCustomerId(customerId.getUserId());
        jpaEntity.setItems(toJpaItems(items));

        //Parent Reference
        for (OrderItemJpaEntity item : jpaEntity.getItems()) {
            item.setOrder(jpaEntity);
        }

        return jpaEntity;

    }

    public Order toDomainObject(OrderJpaEntity jpaEntity) {
        OrderId orderId = OrderId.of(jpaEntity.getId());
        UserId customerId = UserId.of(jpaEntity.getCustomerId());
        OrderStatus status = new OrderStatus(jpaEntity.getStatus());

        List<Order.OrderItem> domainItems = toDomainItems(jpaEntity.getItems());

        Order domainOrder = Order.reconstruct(
                orderId,
                status,
                customerId,
                jpaEntity.getDeadlineDate(),
                jpaEntity.getSubmittedDate(),
                domainItems
        );

        return domainOrder;
    }



    private List<OrderItemJpaEntity> toJpaItems(List<Order.OrderItem> domainItems) {
        List<OrderItemJpaEntity> jpaItems = new ArrayList<>();
        for (Order.OrderItem domainItem : domainItems) {
            OrderItemJpaEntity jpaItem = new OrderItemJpaEntity(
                    domainItem.getItemId(),
                    domainItem.getRequestedQuantity(),
                    null
            );
            jpaItems.add(jpaItem);
        }
        return jpaItems;
    }

    private List<Order.OrderItem> toDomainItems(List<OrderItemJpaEntity> jpaItems) {
        List<Order.OrderItem> domainItems = new ArrayList<>();

        for (OrderItemJpaEntity jpaItem : jpaItems) {
            Order.OrderItem domainItem = new Order.OrderItem(
                    jpaItem.getItemId(),
                    jpaItem.getRequestedQuantity()
            );
            domainItems.add(domainItem);
        }

        return domainItems;
    }



}
