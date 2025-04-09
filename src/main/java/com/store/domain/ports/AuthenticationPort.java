package com.store.domain.ports;

public interface AuthenticationPort {

    String authenticate(String login, String password);
    void register(String login, String password);
}
