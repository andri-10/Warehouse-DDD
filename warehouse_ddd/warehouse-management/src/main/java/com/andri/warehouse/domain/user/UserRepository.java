package com.andri.warehouse.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    Optional<User> findById(UserId userId);
    boolean existsById(UserId userId);
    void delete(UserId userId);

    Optional<User> findByUsername(Username username);
    Optional<User> findByEmail(Email email);
    List<User> findByRole(UserRole role);

    boolean existsByUsername(Username username);
    boolean existsByEmail(Email email);

    List<User> findAllUsers();
    List<User> findClients();
    List<User> findWarehouseManagers();
}