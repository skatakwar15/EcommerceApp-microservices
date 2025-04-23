package com.sarthak.order.kafka;

import com.sarthak.order.model.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private static final String TOPIC = "my-topic";

    @Autowired
    private KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        //kafkaTemplate.send(TOPIC, message);
    }

    public void OrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending confirmation message: " + orderConfirmation);

        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}