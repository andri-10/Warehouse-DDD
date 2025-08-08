package com.andri.warehouse.domain.order;

import com.andri.warehouse.domain.user.UserId;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(OrderId orderId);
    boolean existsById(OrderId orderId);
    void delete(OrderId orderId);

    List<Order> findByCustomerId(UserId customerId);
    List<Order> findByOrderStatus(OrderStatus status);
    List<Order> findOrdersAwaitingApproval();
    List<Order> findByCustomerIdAndOrderStatus(UserId customerId, OrderStatus status);
    List<Order> findOrdersWithDeadLineBefore(LocalDateTime deadline);

}
