package br.com.cedran.rabbit.gateway.rabbitmq;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

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
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @Override
    public void startAllConsumers() {
        final Collection<MessageListenerContainer> containers = rabbitListenerEndpointRegistry.getListenerContainers();
        Set<String> ids = rabbitListenerEndpointRegistry.getListenerContainerIds();
        for (String id : ids) {
            System.out.println(id+"+++++++");
        }

        for (MessageListenerContainer container : containers) {

            String[] queueNames = ((SimpleMessageListenerContainer) container).getQueueNames();
            for (int i = 0; i < queueNames.length; i++) {
                System.out.println("------+++>"+queueNames[i]);
            }
        }        containers.forEach(Lifecycle::start);
        log.info("All consumers were started");
    }

    @Override
    public void start(String queueName) {

        rabbitListenerEndpointRegistry.getListenerContainers().stream().map(container -> (SimpleMessageListenerContainer) container)
                        .filter(message -> Arrays.asList(message.getQueueNames()).contains(queueName)).forEach(container -> {
                            container.start();
                            log.info("Staring queue {} from SimpleRabbitListenerContainerFactory");
                        });
    }

    @Override
    public void stopAllConsumers() {
        final Collection<MessageListenerContainer> containers = rabbitListenerEndpointRegistry.getListenerContainers();
        for (MessageListenerContainer container : containers) {
            String[] queueNames = ((SimpleMessageListenerContainer) container).getQueueNames();
            for (int i = 0; i < queueNames.length; i++) {
                System.out.println("------>"+queueNames[i]);
            }
        }
        containers.forEach(Lifecycle::stop);
        log.info("All consumers were stopped");
    }
}
