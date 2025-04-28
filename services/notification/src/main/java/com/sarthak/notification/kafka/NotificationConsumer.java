package com.sarthak.notification.kafka;

import com.sarthak.notification.kafka.email.EmailService;
import com.sarthak.notification.kafka.order.OrderConfirmation;
import com.sarthak.notification.kafka.payment.PaymentConfirmation;
import com.sarthak.notification.model.NotificationType;
import com.sarthak.notification.notifications.Notification;
import com.sarthak.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(
            topics = "payment-topic",
            groupId = "paymentGroup",
            containerFactory = "paymentKafkaListenerContainerFactory")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from Payment-topic: <{}>", paymentConfirmation);

        Notification savedNotification = notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());

        //todo email send

        String customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "orderGroup",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic: <{}>", orderConfirmation);

        Notification savedNotification = notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build());

        //todo email send
        String customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }
}
