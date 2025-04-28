package com.sarthak.payment.service;

import com.sarthak.payment.entity.Payment;
import com.sarthak.payment.model.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(@Valid PaymentRequest paymentRequest) {

        return Payment.builder()
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .build();
    }
}
