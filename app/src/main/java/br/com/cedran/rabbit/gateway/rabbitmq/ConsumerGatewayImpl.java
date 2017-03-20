package br.com.cedran.rabbit.gateway.rabbitmq;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.gateway.ConsumerGateway;

@Component
public class ConsumerGatewayImpl implements ConsumerGateway {

    @Autowired
    private SimpleMessageListenerContainer listenerContainer;

    @Override
    public boolean isActive() {
        return listenerContainer.isActive();
    }

    @Override
    public boolean isInactive() {
        return !listenerContainer.isActive();
    }

    @Override
    public void start() {
        listenerContainer.start();
    }

    @Override
    public void stop() {
        listenerContainer.stop();
    }
}
