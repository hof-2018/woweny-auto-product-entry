package com.hof.wovenyautoproductentry.scheduler;

import com.hof.wovenyautoproductentry.manager.ChromeWebDriverManager;
import com.hof.wovenyautoproductentry.service.EtsyRugEntryService;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulerController {

    private  final EtsyRugEntryService etsyRugEntryService;

    public SchedulerController(EtsyRugEntryService etsyRugEntryService) {
        this.etsyRugEntryService = etsyRugEntryService;
    }

    @Scheduled(cron = "10 11 22 * * ?")
    public void etsyRugEntry(){
        System.out.println(
                "Cron -" + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000*100)
    public void scheduleEtsyRugEntry() throws InterruptedException {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is started.");
        this.etsyRugEntryService.execute();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is finished.");
    }

}
