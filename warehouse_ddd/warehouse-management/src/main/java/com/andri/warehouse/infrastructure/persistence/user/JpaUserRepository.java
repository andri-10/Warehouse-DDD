package com.andri.warehouse.infrastructure.persistence.user;

import com.andri.warehouse.domain.user.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {


    private final UserJpaEntityRepository jpaRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(UserJpaEntityRepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);
        jpaRepository.save(userJpaEntity);
    }

    @Override
    public Optional<User> findById(UserId userId) {

        Optional<UserJpaEntity> jpaEntity = jpaRepository.findById(userId.getUserId());

        return jpaEntity.map(userMapper::toDomainObject);

    }

    @Override
    public boolean existsById(UserId userId) {
        return jpaRepository.existsById(userId.getUserId());
    }

    @Override
    public void delete(UserId userId) {
        jpaRepository.deleteById(userId.getUserId());
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        Optional<UserJpaEntity> jpaEntity = jpaRepository.findByUsername(username.getUsername());

        return jpaEntity.map(userMapper::toDomainObject);

    }

    @Override
    public Optional<User> findByEmail(Email email) {

        Optional<UserJpaEntity> jpaEntity = jpaRepository.findByEmail(email.getEmail());
        return jpaEntity.map(userMapper::toDomainObject);
    }

    @Override
    public List<User> findByRole(UserRole role) {

        List<UserJpaEntity> jpaEntities = jpaRepository.findByRole(role.toString());
        return jpaEntities.stream()
                .map(userMapper::toDomainObject)
                .collect(Collectors.toList());

    }

    @Override
    public boolean existsByUsername(Username username) {
        return jpaRepository.existsByUsername(username.getUsername());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        List<UserJpaEntity> jpaEntities = jpaRepository.findAll();
        return jpaEntities.stream()
                .map(userMapper::toDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findClients() {
        List<UserJpaEntity> clientEntities = jpaRepository.findByRole(UserRole.client().toString());
        return clientEntities.stream()
                .map(userMapper::toDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findWarehouseManagers() {
        List<UserJpaEntity> clientEntities = jpaRepository.findByRole(UserRole.warehouseManager().toString());
        return clientEntities.stream()
                .map(userMapper::toDomainObject)
                .collect(Collectors.toList());
    }
}

