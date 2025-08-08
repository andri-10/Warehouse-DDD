package com.andri.warehouse.application.order;

import com.andri.warehouse.application.order.command.*;
import com.andri.warehouse.application.order.dto.OrderDetailsDTO;
import com.andri.warehouse.application.order.dto.OrderSummaryDTO;
import com.andri.warehouse.application.order.exception.OrderNotFoundException;
import com.andri.warehouse.application.order.query.OrderListQuery;
import com.andri.warehouse.domain.order.Order;
import com.andri.warehouse.domain.order.OrderId;
import com.andri.warehouse.domain.order.OrderRepository;
import com.andri.warehouse.domain.order.OrderStatus;
import com.andri.warehouse.domain.user.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order findOrderOrThrow(OrderId orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Transactional
    public OrderId createOrder(CreateOrderCommand command) {

        UserId customerId = command.getCustomerId();
        LocalDateTime deadlineDate = command.getDeadlineDate();
        Order order = Order.createOrder(customerId, deadlineDate);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void submitOrder(SubmitOrderCommand command) {

        Order order = findOrderOrThrow(command.getOrderId());
        order.submit();
        orderRepository.save(order);
    }

    @Transactional
    public void addItemToOrder(AddItemCommand command) {

        Order order = findOrderOrThrow(command.getOrderId());

        String itemId = command.getItemId();
        int quantity = command.getQuantity();

        order.addItem(itemId, quantity);
        orderRepository.save(order);

    }

    @Transactional
    public void updateItemQuantity(UpdateItemQuantityCommand command){
        Order order = findOrderOrThrow(command.getOrderId());
        String itemId = command.getItemId();
        int quantity = command.getQuantity();

        order.updateItemQuantity(itemId, quantity);
        orderRepository.save(order);

    }

    @Transactional
    public void removeItemFromOrder(RemoveItemCommand command) {
        Order order = findOrderOrThrow(command.getOrderId());
        String itemId = command.getItemId();
        order.removeItem(itemId);
        orderRepository.save(order);
    }

    @Transactional
    public void approveOrder(ApproveOrderCommand command) {
        Order order = findOrderOrThrow(command.getOrderId());
        order.approve();
        orderRepository.save(order);
    }

    @Transactional
    public void declineOrder(DeclineOrderCommand command) {
        Order order = findOrderOrThrow(command.getOrderId());
        order.decline();
        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(CancelOrderCommand command) {
        Order order = findOrderOrThrow(command.getOrderId());
        order.cancel();
        orderRepository.save(order);
    }

    public OrderDetailsDTO getOrderDetails(OrderId orderId) {
        Order order = findOrderOrThrow(orderId);
        return OrderDetailsDTO.from(order);
    }

    public List<OrderSummaryDTO> getOrders(OrderListQuery query){
        List<Order> orders;

        if(query.hasCustomerId() && query.hasStatus()){
            UserId customerIdObj = UserId.of(query.getCustomerId());
            OrderStatus statusObj = new OrderStatus(query.getStatus());
            orders = orderRepository.findByCustomerIdAndOrderStatus(customerIdObj, statusObj);
        }else if(query.hasCustomerId()){
            UserId customerIdObj = UserId.of(query.getCustomerId());
            orders = orderRepository.findByCustomerId(customerIdObj);
        }else if(query.hasStatus()){
            OrderStatus statusObj = new OrderStatus(query.getStatus());
            orders = orderRepository.findByOrderStatus(statusObj);
        }else{
            orders = orderRepository.findOrdersAwaitingApproval();
        }

        return orders.stream()
                .map(OrderSummaryDTO::from)
                .collect(Collectors.toList());

    }
}

