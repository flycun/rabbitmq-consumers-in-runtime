package br.com.cedran.rabbit.gateway.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.gateway.ConsumerGateway;
import lombok.extern.slf4j.Slf4j;

@Component
@ManagedResource(objectName = "br.com.cedran.rabbit.gateway.jmx:type=ConsumerJmx", description = "Manage rabbit consumers")
@Slf4j
public class ConsumerJmx {

    @Autowired
    private ConsumerGateway consumerGateway;

    @ManagedOperation(description = "Stop all consumers")
    public void stopAllConsumers() {
        consumerGateway.stopAllConsumers();
    }

    @ManagedOperation(description = "Start all consumers")
    public void startAllConsumers() {
        consumerGateway.startAllConsumers();
    }

    @ManagedOperation(description = "Start consumers of an specific queue")
    @ManagedOperationParameters({ @ManagedOperationParameter(name = "queueName", description = "Name of the queue") })
    public void startConsumers(String queueName) {
        consumerGateway.start(queueName);
    }
}
