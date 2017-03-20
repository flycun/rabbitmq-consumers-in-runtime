package br.com.cedran.rabbit.gateway.rabbitmq;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.gateway.ConsumerGateway;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerGatewayImpl implements ConsumerGateway {

    @Autowired
    private SimpleMessageListenerContainer simpleMessageListenerContainer;

    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @Override
    public void startAllConsumers() {
        simpleMessageListenerContainer.start();
        final Collection<MessageListenerContainer> containers = rabbitListenerEndpointRegistry.getListenerContainers();
        containers.forEach(Lifecycle::start);
        log.info("All consumers were started");
    }

    @Override
    public void start(String queueName) {
        if (Arrays.asList(simpleMessageListenerContainer.getQueueNames()).contains(queueName)) {
            log.info("Staring queue {} from SimpleMessageListenerContainer");
            simpleMessageListenerContainer.start();
        }
        rabbitListenerEndpointRegistry.getListenerContainers().stream().map(container -> (SimpleMessageListenerContainer) container)
                        .filter(message -> Arrays.asList(message.getQueueNames()).contains(queueName)).forEach(container -> {
                            container.start();
                            log.info("Staring queue {} from SimpleRabbitListenerContainerFactory");
                        });
    }

    @Override
    public void stopAllConsumers() {
        simpleMessageListenerContainer.stop();
        final Collection<MessageListenerContainer> containers = rabbitListenerEndpointRegistry.getListenerContainers();
        containers.forEach(Lifecycle::stop);
        log.info("All consumers were stopped");
    }
}
