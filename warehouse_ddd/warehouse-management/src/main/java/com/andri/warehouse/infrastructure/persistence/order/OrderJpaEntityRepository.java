package com.andri.warehouse.infrastructure.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderJpaEntityRepository extends JpaRepository<OrderJpaEntity, String> {
    List<OrderJpaEntity> findByCustomerId(String customerId);

    List<OrderJpaEntity> findByStatus(String status);

    @Query("Select o FROM OrderJpaEntity o WHERE o.status = 'AWAITNG_APPROVAL'")
    List<OrderJpaEntity> findOrdersAwaitingApproval();

    List<OrderJpaEntity> findByCustomerIdAndStatus(String customerId, String status);

    List<OrderJpaEntity> findByDeadlineDateBefore(LocalDateTime deadline);
}
