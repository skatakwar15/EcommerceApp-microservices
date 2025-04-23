package com.sarthak.notification.kafka.order;

import com.sarthak.notification.model.Customer;
import com.sarthak.notification.model.PaymentMethod;
import com.sarthak.notification.model.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
