package br.com.cedran.rabbit.gateway.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.cedran.rabbit.usecases.EnableDisableConsumer;

@Component
public class EnableDisableConsumerJob {

    private EnableDisableConsumer enableDisableConsumer;

    @Autowired
    public EnableDisableConsumerJob(EnableDisableConsumer enableDisableConsumer) {
        this.enableDisableConsumer = enableDisableConsumer;
    }

    @Scheduled(cron = "${job.periodicity:0 * * * * *}")
    public void start() {
        this.enableDisableConsumer.execute();
    }

}
