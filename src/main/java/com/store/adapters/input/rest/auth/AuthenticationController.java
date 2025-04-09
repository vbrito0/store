package com.store.adapters.input.rest.auth;

import com.store.adapters.input.rest.auth.dto.LoginRequest;
import com.store.adapters.input.rest.auth.dto.LoginResponse;
import com.store.domain.ports.AuthenticationPort;
import com.store.domain.ports.repository.UserRepositoryPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final AuthenticationPort authenticationPort;

    @Autowired
    public AuthenticationController(AuthenticationPort authenticationPort) {
        this.authenticationPort = authenticationPort;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = authenticationPort.authenticate(loginRequest.login(), loginRequest.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    //TODO POR ENQUANTO NÃO UTILIZAR
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid LoginRequest loginRequest) {
        authenticationPort.register(loginRequest.login(), loginRequest.password());

        return ResponseEntity.ok().build();
    }
}
