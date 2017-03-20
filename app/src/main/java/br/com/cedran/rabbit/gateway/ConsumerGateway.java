package br.com.cedran.rabbit.gateway;

public interface ConsumerGateway {

    boolean isActive();

    boolean isInactive();

    void start();

    void stop();
}
