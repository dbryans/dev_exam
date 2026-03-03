
package com.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.config.RabbitConfig;
import com.demo.payment.PaymentStatusChangedEvent;

@Component
public class AuditConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_AUDIT)
    public void handle(PaymentStatusChangedEvent event) {
        System.out.println("Audit registered for payment: " + event.getPaymentId() + " with status: " + event.getStatus());
    }
}
