package com.andri.warehouse.infrastructure.persistence.order;

import com.andri.warehouse.domain.order.Order;
import com.andri.warehouse.domain.order.OrderId;
import com.andri.warehouse.domain.order.OrderRepository;
import com.andri.warehouse.domain.order.OrderStatus;
import com.andri.warehouse.domain.user.UserId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final OrderJpaEntityRepository jpaRepository;
    private final OrderMapper orderMapper;

    public JpaOrderRepository(OrderJpaEntityRepository jpaRepository, OrderMapper orderMapper) {
        this.jpaRepository = jpaRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public void save(Order order) {

        OrderJpaEntity jpaEntity = orderMapper.toJpaEntity(order);
        jpaRepository.save(jpaEntity);

    }

    @Override
    public Optional<Order> findById(OrderId orderId) {

        Optional<OrderJpaEntity> jpaEntity = jpaRepository.findById(orderId.getOrderId());

        return jpaEntity.map(orderMapper::toDomainObject);

    }

    @Override
    public boolean existsById(OrderId orderId) {

        return jpaRepository.existsById(orderId.getOrderId());

    }

    @Override
    public void delete(OrderId orderId) {

       jpaRepository.deleteById(orderId.getOrderId());

    }

    @Override
    public List<Order> findByCustomerId(UserId customerId) {

        List<OrderJpaEntity> jpaEntities = jpaRepository.findByCustomerId(customerId.getUserId());

        return jpaEntities.stream()
                .map(orderMapper::toDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus status) {

        List<OrderJpaEntity> jpaEntities = jpaRepository.findByStatus(status.getStatus().toString());

        return jpaEntities.stream()
                .map(orderMapper::toDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrdersAwaitingApproval() {

        List<OrderJpaEntity> jpaEntities = jpaRepository.findOrdersAwaitingApproval();
        return jpaEntities.stream()
                .map(orderMapper::toDomainObject)
                .collect(Collectors.toList());

    }

    @Override
    public List<Order> findByCustomerIdAndOrderStatus(UserId customerId, OrderStatus status) {

        List<OrderJpaEntity> jpaEntities = jpaRepository.findByCustomerIdAndStatus(customerId.getUserId(), status.getStatus().toString());

        return jpaEntities.stream()
                .map(orderMapper::toDomainObject)
                .collect(Collectors.toList());

    }

    @Override
    public List<Order> findOrdersWithDeadLineBefore(LocalDateTime deadline) {

        List<OrderJpaEntity> jpaEntities = jpaRepository.findByDeadlineDateBefore(deadline);

        return jpaEntities.stream()
                .map(orderMapper::toDomainObject)
                .collect(Collectors.toList());

    }
}
