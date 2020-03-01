package br.com.cedran.rabbit.config.rabbitmq;

import br.com.cedran.rabbit.model.ImportantMessage;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Value(value = "${concurrentConsumers:1}")
    private Integer concurrentConsumers;

    @Value(value = "${maxConcurrentConsumers:2}")
    private Integer maxConcurrentConsumers;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("172.26.165.28");
        factory.setUsername("volte1");
        factory.setPassword("123456");
        factory.setVirtualHost("volte1");
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory2() {
        CachingConnectionFactory factory = new CachingConnectionFactory("172.26.165.28");
        factory.setUsername("volte2");
        factory.setPassword("123456");
        factory.setVirtualHost("/volte2");
        return factory;
    }




    @Bean
    Jackson2JsonMessageConverter jacksonConverter() {
        Jackson2JsonMessageConverter jacksonConverter = new Jackson2JsonMessageConverterCustom(ImportantMessage.class);
        return jacksonConverter;
    }


    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainer(final ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory listenerFactory = new SimpleRabbitListenerContainerFactory();
        listenerFactory.setConnectionFactory(connectionFactory);
        listenerFactory.setConcurrentConsumers(1);
        listenerFactory.setMaxConcurrentConsumers(1);
        return listenerFactory;
    }

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainer2(final ConnectionFactory connectionFactory2) {
        SimpleRabbitListenerContainerFactory listenerFactory = new SimpleRabbitListenerContainerFactory();
        listenerFactory.setConnectionFactory(connectionFactory2);
        listenerFactory.setConcurrentConsumers(2);
        listenerFactory.setMaxConcurrentConsumers(2);
        return listenerFactory;
    }
}
