package com.devshaks.ecommerce.kafka;

import com.devshaks.ecommerce.email.EmailService;
import com.devshaks.ecommerce.kafka.order.OrderConfirmation;
import com.devshaks.ecommerce.kafka.payment.PaymentConfirmation;
import com.devshaks.ecommerce.notification.Notification;
import com.devshaks.ecommerce.notification.NotificationRepository;
import com.devshaks.ecommerce.notification.NotificationType;
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

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info(String.format("Consumed payment confirmation: %s", paymentConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info(String.format("Consumed order confirmation: %s", orderConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation)
                .build());
    }



}
