package com.sarthak.notification.repository;

import com.sarthak.notification.kafka.payment.PaymentConfirmation;
import com.sarthak.notification.notifications.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
