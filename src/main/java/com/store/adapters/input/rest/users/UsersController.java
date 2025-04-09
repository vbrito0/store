package com.store.adapters.input.rest.users;

import com.store.adapters.input.rest.users.dto.UsersRequest;
import com.store.domain.ports.UserPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UserPort userPort;

    @Autowired
    public UsersController(UserPort userPort) {
        this.userPort = userPort;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid UsersRequest users) {
        try {
            userPort.createUser(users.username(), users.email(), users.password());
            new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long userId) {
        try {
            userPort.deleteUser(userId);
            new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
