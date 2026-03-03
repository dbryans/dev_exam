package com.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "payment.exchange";
    public static final String ROUTING_KEY = "payment.status.changed";
    public static final String QUEUE_NOTIFICATION = "payment.notification.queue";
    public static final String QUEUE_AUDIT = "payment.audit.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NOTIFICATION);
    }

    @Bean
    public Queue auditQueue() {
        return new Queue(QUEUE_AUDIT);
    }

    @Bean
    public Binding bindNotification() {
        return BindingBuilder.bind(notificationQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Binding bindAudit() {
        return BindingBuilder.bind(auditQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }

  
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //JSON Config
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}