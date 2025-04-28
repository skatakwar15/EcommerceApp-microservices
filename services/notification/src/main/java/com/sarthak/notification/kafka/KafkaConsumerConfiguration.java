package com.sarthak.notification.kafka;

import com.sarthak.notification.kafka.order.OrderConfirmation;
import com.sarthak.notification.kafka.payment.PaymentConfirmation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, PaymentConfirmation> paymentConsumerFactory() {
        return createFactory(PaymentConfirmation.class);
    }

    @Bean
    public ConsumerFactory<String, OrderConfirmation> orderConsumerFactory() {
        return createFactory(OrderConfirmation.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> paymentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }

    private <T> ConsumerFactory<String, T> createFactory(Class<T> targetType) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(targetType);
        deserializer.addTrustedPackages("com.sarthak.notification.kafka"); // Replace with relevant package
        deserializer.setRemoveTypeHeaders(false); // Retain type headers for deserialization
        deserializer.setUseTypeMapperForKey(true); // Set key mapping rules if applicable
        deserializer.setUseTypeMapperForKey(false); // Allows deserialization without relying on Kafka headers

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer.getClass().getName());

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }
}