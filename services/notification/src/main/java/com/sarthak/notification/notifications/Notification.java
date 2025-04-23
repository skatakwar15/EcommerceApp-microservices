package com.sarthak.notification.notifications;


import com.sarthak.notification.kafka.order.OrderConfirmation;
import com.sarthak.notification.kafka.payment.PaymentConfirmation;
import com.sarthak.notification.model.NotificationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
