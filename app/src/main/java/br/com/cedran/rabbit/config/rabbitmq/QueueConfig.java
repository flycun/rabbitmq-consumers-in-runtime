package br.com.cedran.rabbit.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    Queue importantMessageQueueSimpleMessage() {
        return new Queue("importantMessageQueueSimpleMessage", true);
    }

    @Bean
    Queue importantMessageQueueSimpleRabbit() {
        return new Queue("importantMessageQueueSimpleRabbit", true);
    }

    @Bean
    FanoutExchange messageExchange() {
        return new FanoutExchange("messageExchange");
    }

    @Bean
    Binding bindingImportantMessage1(Queue importantMessageQueueSimpleMessage, FanoutExchange messageExchange) {
        return BindingBuilder.bind(importantMessageQueueSimpleMessage).to(messageExchange);
    }

    @Bean
    Binding bindingImportantMessage2(Queue importantMessageQueueSimpleRabbit, FanoutExchange messageExchange) {
        return BindingBuilder.bind(importantMessageQueueSimpleRabbit).to(messageExchange);
    }
}
