package br.com.cedran.rabbit.gateway.rabbitmq;

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

    public void execute(ImportantMessage importantMessage) {
        try {
            processImportantMessage.execute(importantMessage);
        } catch (Exception e) {
            log.error("Error while processing message {}", e);
        }
    }

}
