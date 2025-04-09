package com.store.domain.ports;

import com.store.domain.model.Users;

public interface TokenPort {

    String generateToken(Users users);
    String validateToken(String token);
}
