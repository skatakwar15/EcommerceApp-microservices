package com.sarthak.order.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic orderTopic() {

        return TopicBuilder
                .name("order-topic")
                .build();

        //return new NewTopic("order", 1, (short) 1);
    }
}
