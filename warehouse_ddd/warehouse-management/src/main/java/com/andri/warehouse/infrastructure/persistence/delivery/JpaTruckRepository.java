package com.andri.warehouse.infrastructure.persistence.delivery;

import com.andri.warehouse.domain.delivery.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaTruckRepository implements TruckRepository {

    private final TruckJpaEntityRepository jpaEntityRepository;

    public JpaTruckRepository(TruckJpaEntityRepository jpaEntityRepository) {
        this.jpaEntityRepository = jpaEntityRepository;
    }

    @Override
    public void save(Truck truck) {
        TruckJpaEntity entity = TruckMapper.toJpaEntity(truck);
        jpaEntityRepository.save(entity);
    }

    @Override
    public void update(Truck truck) {
        TruckJpaEntity entity = TruckMapper.toJpaEntity(truck);
        jpaEntityRepository.save(entity);
    }

    @Override
    public void delete(TruckId truckId) {
        jpaEntityRepository.deleteById(truckId.getTruckId());
    }

    @Override
    public Optional<Truck> findById(TruckId truckId) {
        return jpaEntityRepository.findById(truckId.getTruckId())
                .map(TruckMapper::toDomain);
    }

    @Override
    public List<Truck> findAll() {
        return jpaEntityRepository.findAll()
                .stream()
                .map(TruckMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Truck> findAvailableOn(LocalDate date) {
        return jpaEntityRepository.findAvailableOn(date)
                .stream()
                .map(TruckMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByChassisNumber(ChassisNumber chassisNumber) {
        return jpaEntityRepository.existsByChassisNumber(chassisNumber.getChassisNumber());
    }

    @Override
    public boolean existsByLicensePlate(LicensePlate licensePlate) {
        return jpaEntityRepository.existsByLicensePlate(licensePlate.getLicensePlate());
    }
}