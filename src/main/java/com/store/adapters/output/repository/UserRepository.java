package com.store.adapters.output.repository;

import com.store.domain.model.Users;
import com.store.domain.ports.repository.UserRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>, UserRepositoryPort {
    Users save(Users users);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUserId(Long userId);
    void delete(Users users);
}
