package br.com.cedran.rabbit.usecases;

import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.model.ImportantMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcessImportantMessage {

    public void execute(ImportantMessage importantMessage) throws InterruptedException {
        log.info("Processing and applying important business rules according to the message received: {}", importantMessage);
        Thread.sleep(3000);
        log.info("Process finished.");
    }
}
