package br.com.cedran.rabbit.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    Queue importantMessageQueue() {
        return new Queue("importantMessageQueue", true);
    }

    @Bean
    DirectExchange messageExchange() {
        return new DirectExchange("messageExchange");
    }

    @Bean
    Binding bindingImportantMessage(Queue importantMessageQueue, DirectExchange messageExchange) {
        return BindingBuilder.bind(importantMessageQueue).to(messageExchange).with("important");
    }
}
