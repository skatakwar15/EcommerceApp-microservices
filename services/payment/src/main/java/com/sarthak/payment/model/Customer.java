package com.sarthak.payment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "firstname not to be null")
        String firstname,
        @NotNull
        String lastname,
        @NotNull
        @Email
        String email
) {
}
