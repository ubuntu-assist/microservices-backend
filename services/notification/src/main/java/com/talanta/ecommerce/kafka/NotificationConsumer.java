package com.talanta.ecommerce.kafka;

import com.talanta.ecommerce.email.EmailService;
import com.talanta.ecommerce.kafka.order.OrderConfirmation;
import com.talanta.ecommerce.kafka.payment.PaymentConfirmation;
import com.talanta.ecommerce.notification.Notification;
import com.talanta.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.talanta.ecommerce.notification.NotificationType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from the payment-topic Topic:: {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                "%s %s".formatted(paymentConfirmation.customerFirstName(), paymentConfirmation.customerLastName()),
                paymentConfirmation.totalAmount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from the order-topic Topic:: {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                "%s %s".formatted(orderConfirmation.customer().firstName(), orderConfirmation.customer().lastName()),
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
