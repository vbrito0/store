package com.store.domain.service;

import com.store.domain.model.Users;
import com.store.domain.ports.AuthenticationPort;
import com.store.domain.ports.TokenPort;
import com.store.domain.ports.repository.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepositoryPort;
    private final TokenPort tokenPort;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, UserRepositoryPort userRepositoryPort, TokenPort tokenPort) {
        this.authenticationManager = authenticationManager;
        this.userRepositoryPort = userRepositoryPort;
        this.tokenPort = tokenPort;
    }

    @Override
    public String authenticate(String login, String password) {
        var userPassword = new UsernamePasswordAuthenticationToken(login, password);
        var authentication = this.authenticationManager.authenticate(userPassword);

        var token = this.tokenPort.generateToken((Users) authentication.getPrincipal());
        return token;
    }

    //TODO POR ENQUANTO NÃO UTILIZAR
    @Override
    public void register(String login, String password) {
        Optional<Users> user = userRepositoryPort.findByEmail(login);
        if(user.isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
            Users users = new Users(login, encryptedPassword);
            userRepositoryPort.save(users);
        }
    }
}
