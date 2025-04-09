package com.store.domain.utils;

import com.store.domain.model.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataUtil {

    public Users converterUsers(String username, String email, String password) {
        new Users();
        return Users.builder()
                    .username(username)
                    .email(email)
                    .password(new BCryptPasswordEncoder().encode(password)).build();
    }
}
