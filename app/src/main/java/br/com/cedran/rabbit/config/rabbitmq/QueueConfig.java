package br.com.cedran.rabbit.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {


    @Bean
    Queue importantMessageQueueSimpleRabbit(AmqpAdmin amqpAdmin, AmqpAdmin amqpAdmin2) {
        Queue querue = new Queue("importantMessageQueueSimpleRabbit", true);
        amqpAdmin.declareQueue(querue);
        amqpAdmin2.declareQueue(querue);
        return querue;
    }

    @Bean
    FanoutExchange messageExchange() {
        return new FanoutExchange("messageExchange");
    }


    @Bean
    Binding bindingImportantMessage2(Queue importantMessageQueueSimpleRabbit, FanoutExchange messageExchange) {
        return BindingBuilder.bind(importantMessageQueueSimpleRabbit).to(messageExchange);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public AmqpAdmin amqpAdmin2(ConnectionFactory connectionFactory2) {
        return new RabbitAdmin(connectionFactory2);
    }
}
