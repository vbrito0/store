package com.store.domain.ports.repository;

import com.store.domain.model.Users;

import java.util.Optional;

public interface UserRepositoryPort {
    Users save(Users users);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUserId(Long userId);
    void delete(Users users);
}
