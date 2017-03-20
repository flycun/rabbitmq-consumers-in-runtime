package br.com.cedran.rabbit.usecases;

import java.time.LocalTime;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EnableDisableConsumer {

    @Autowired
    private SimpleMessageListenerContainer listenerContainer;

    public void execute() {
        LocalTime time = LocalTime.now();
        if (time.getMinute() % 2 == 0) {
            log.info("Starting consumer");
            if (!listenerContainer.isActive()) {
                listenerContainer.start();
            }
        } else {
            log.info("Stoping consumer");
            if (listenerContainer.isActive()) {
                listenerContainer.stop();
            }
        }
    }
}
