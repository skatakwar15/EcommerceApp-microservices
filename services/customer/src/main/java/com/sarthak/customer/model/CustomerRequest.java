package com.sarthak.customer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        @Email
        String email,
        Address address
) {

}
