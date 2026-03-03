
package com.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.config.RabbitConfig;
import com.demo.payment.PaymentStatusChangedEvent;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICATION)
   
    public void handle(PaymentStatusChangedEvent event) {

        System.out.println(
            "Notification received: "
            + event.getPaymentId()
            + " with status: "
            + event.getStatus()
        );
    }
}
