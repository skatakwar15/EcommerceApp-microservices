package com.sarthak.payment.service;

import com.sarthak.payment.entity.Payment;
import com.sarthak.payment.kafka.NotificationProducer;
import com.sarthak.payment.model.PaymentNotificationRequest;
import com.sarthak.payment.model.PaymentRequest;
import com.sarthak.payment.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private NotificationProducer notificationProducer;


    public Integer createPayment(@Valid PaymentRequest paymentRequest) {
        Payment savedPayment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                )
        );
        return savedPayment.getId();
    }
}
