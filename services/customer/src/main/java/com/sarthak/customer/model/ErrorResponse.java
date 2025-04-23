package com.sarthak.customer.model;

import java.util.Map;

public record ErrorResponse(


        Map<String, String> errors
) {
}
