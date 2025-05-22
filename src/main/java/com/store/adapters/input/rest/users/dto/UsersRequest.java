package com.store.adapters.input.rest.users.dto;

import jakarta.validation.constraints.NotBlank;

public record UsersRequest(
        @NotBlank(message = "username é obrigatório!")
        String username,
        @NotBlank(message = "email é obrigatório!")
        String email,
        @NotBlank(message = "password é obrigatório!")
        String password)
{
}
