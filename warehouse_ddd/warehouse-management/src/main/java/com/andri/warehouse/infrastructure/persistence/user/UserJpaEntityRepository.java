package com.andri.warehouse.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaEntityRepository extends JpaRepository<UserJpaEntity, String> {

    Optional<UserJpaEntity> findByUsername(String username);
    Optional<UserJpaEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<UserJpaEntity> findByRole(String role);

    @Query("Select u FROM UserJpaEntity u WHERE u.role = 'CLIENT'")
    List<UserJpaEntity> findClients();

    @Query("Select u FROM UserJpaEntity u WHERE u.role = 'WAREHOUSE_MANAGER'")
    List<UserJpaEntity> findWarehouseManagers();

    @Query("Select u FROM UserJpaEntity u WHERE u.role = 'SYSTEM_ADMIN'")
    List<UserJpaEntity> findSystemAdmins();

}

