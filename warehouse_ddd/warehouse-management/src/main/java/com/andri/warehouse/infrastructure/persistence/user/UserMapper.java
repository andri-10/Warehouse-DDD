package com.andri.warehouse.infrastructure.persistence.user;

import com.andri.warehouse.domain.user.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserJpaEntity toJpaEntity(User domainUser) {

        UserId userId = domainUser.getUserId();
        Username username = domainUser.getUsername();
        Email email = domainUser.getEmail();
        UserRole role = domainUser.getRole();
        Password password = domainUser.getPassword();

        UserJpaEntity jpaEntity = new UserJpaEntity();

        jpaEntity.setId(userId.getUserId());
        jpaEntity.setUsername(username.getUsername());
        jpaEntity.setEmail(email.getEmail());
        jpaEntity.setRole(role.getRole().toString());
        jpaEntity.setPasswordHash(password.getHashedPassword());

        return jpaEntity;
    }

    public User toDomainObject(UserJpaEntity jpaEntity){

        UserId userId = UserId.of(jpaEntity.getId());
        Username username = Username.of(jpaEntity.getUsername());
        Email email = Email.of(jpaEntity.getEmail());
        UserRole role = new UserRole(jpaEntity.getRole());
        Password password = Password.fromHash(jpaEntity.getPasswordHash());

        return User.reconstruct(
                userId,
                username,
                email,
                role,
                password
        );

    }
}
