package com.sarthak.payment.kafka;


import com.sarthak.payment.model.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest notification) {
        log.info("Sending payment notification: <{}>", notification);
        Message<PaymentNotificationRequest> message =
                MessageBuilder.withPayload(notification)
                        .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                        .build();
        kafkaTemplate.send(message);

    }
}
