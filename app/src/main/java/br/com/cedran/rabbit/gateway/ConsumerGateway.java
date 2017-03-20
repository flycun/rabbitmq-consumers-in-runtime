package br.com.cedran.rabbit.gateway;

public interface ConsumerGateway {

    void startAllConsumers();

    void start(String queueName);

    void stopAllConsumers();
}
