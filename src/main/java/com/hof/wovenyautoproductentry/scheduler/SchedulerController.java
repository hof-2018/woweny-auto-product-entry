package com.hof.wovenyautoproductentry.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerController {


    @Scheduled(cron = "50 24 21 * * ?")
    public void etsyRugEntry(){
        System.out.println(
                "Cron -" + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
