package com.sarthak.order.model;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
