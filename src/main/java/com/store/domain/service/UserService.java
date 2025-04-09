package com.store.domain.service;

import com.store.domain.exception.NotFoundUserException;
import com.store.domain.model.Users;
import com.store.domain.ports.UserPort;
import com.store.domain.ports.repository.UserRepositoryPort;
import com.store.domain.utils.UserDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final UserDataUtil userDataUtil;

    @Autowired
    public UserService(UserRepositoryPort userRepositoryPort, UserDataUtil userDataUtil) {
        this.userRepositoryPort = userRepositoryPort;
        this.userDataUtil = userDataUtil;
    }

    @Override
    public void createUser(String username, String email, String password) {
        try {
            Optional<Users> user = userRepositoryPort.findByEmail(email);
            if(user.isEmpty()) {
                Users users = userDataUtil.converterUsers(username, email, password);
                userRepositoryPort.save(users);
            }
        } catch (Exception e) {
            throw new RuntimeException("Esse usuário já existe!");
        }

    }

    @Override
    public void deleteUser(Long userId) {
        Users users = userRepositoryPort.findByUserId(userId).orElseThrow(() -> new NotFoundUserException("Não existe esse usuário!"));
        userRepositoryPort.delete(users);
    }
}
