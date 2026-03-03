
package com.demo.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.demo.config.RabbitConfig;
import com.demo.entity.Payment;
import com.demo.payment.PaymentStatusChangedEvent;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Payment payment) {
        System.out.println("Publishing event for payment: " + payment.getId() + " with status: " + payment.getStatus());
        PaymentStatusChangedEvent event =
                PaymentStatusChangedEvent.builder()
                        .paymentId(payment.getId())
                        .status(payment.getStatus())
                        .totalAmount(payment.getTotalAmount())
                        
                        .build();

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                event
        );
    }
}
