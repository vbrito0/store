package com.store.domain.ports;

public interface UserPort {
    void createUser(String username, String email, String password);
    void deleteUser(Long userId);
}
