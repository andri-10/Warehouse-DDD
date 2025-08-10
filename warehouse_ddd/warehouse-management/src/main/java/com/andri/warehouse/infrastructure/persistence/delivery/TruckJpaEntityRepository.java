package com.andri.warehouse.infrastructure.persistence.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TruckJpaEntityRepository extends JpaRepository<TruckJpaEntity, String> {

    boolean existsByChassisNumber(String chassisNumber);

    boolean existsByLicensePlate(String licensePlate);

    @Query("SELECT t FROM TruckJpaEntity t WHERE t.reservedDate IS NULL OR t.reservedDate != :date")
    List<TruckJpaEntity> findAvailableOn(@Param("date") LocalDate date);
}