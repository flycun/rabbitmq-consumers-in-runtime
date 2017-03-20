package br.com.cedran.rabbit.usecases;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.gateway.ConsumerGateway;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EnableDisableConsumer {

    private ConsumerGateway consumerGateway;

    @Autowired
    public EnableDisableConsumer(ConsumerGateway consumerGateway) {
        this.consumerGateway = consumerGateway;
    }

    public void execute() {
        LocalTime time = LocalTime.now();
        if (time.getMinute() % 2 == 0) {
            log.info("Starting consumer");
            if (consumerGateway.isInactive()) {
                consumerGateway.start();
            }
        } else {
            log.info("Stoping consumer");
            if (consumerGateway.isActive()) {
                consumerGateway.stop();
            }
        }
    }
}
