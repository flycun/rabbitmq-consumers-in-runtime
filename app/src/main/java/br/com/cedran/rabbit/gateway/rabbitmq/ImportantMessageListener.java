package br.com.cedran.rabbit.gateway.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.model.ImportantMessage;
import br.com.cedran.rabbit.usecases.ProcessImportantMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImportantMessageListener {

    private ProcessImportantMessage processImportantMessage;

    @Autowired
    public ImportantMessageListener(ProcessImportantMessage processImportantMessage) {
        this.processImportantMessage = processImportantMessage;
    }


    @RabbitListener(queues = "importantMessageQueueSimpleRabbit",id = "a")
    public void execute(ImportantMessage importantMessage) {
        try {
            log.info("Started by SimpleRabbitListenerContainerFactory");
            processImportantMessage.execute(importantMessage);
        } catch (Exception e) {
            log.error("Error while processing message {}", e);
        }
    }

    @RabbitListener(queues = "importantMessageQueueSimpleRabbit" ,id = "b",containerFactory = "simpleRabbitListenerContainer2")
    public void execute2(ImportantMessage importantMessage) {
        try {
            log.info("Started by SimpleRabbitListenerContainerFactory2");
            processImportantMessage.execute(importantMessage);
        } catch (Exception e) {
            log.error("Error while processing message {}", e);
        }
    }

}
