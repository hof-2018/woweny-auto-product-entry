package com.hof.wovenyautoproductentry.scheduler;

import com.hof.wovenyautoproductentry.service.ChairishPillowEntryService;
import com.hof.wovenyautoproductentry.service.ChairishRugEntryService;
import com.hof.wovenyautoproductentry.service.EtsyPillowEntryService;
import com.hof.wovenyautoproductentry.service.EtsyRugEntryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulerController {

    private final EtsyRugEntryService etsyRugEntryService;
    private final ChairishRugEntryService chairishRugEntryService;
    private final ChairishPillowEntryService chairishPillowEntryService;
    private final EtsyPillowEntryService etsyPillowEntryService;

    public SchedulerController(EtsyRugEntryService etsyRugEntryService, ChairishRugEntryService chairishRugEntryService, ChairishPillowEntryService chairishPillowEntryService, EtsyPillowEntryService etsyPillowEntryService) {
        this.etsyRugEntryService = etsyRugEntryService;
        this.chairishRugEntryService = chairishRugEntryService;
        this.chairishPillowEntryService = chairishPillowEntryService;
        this.etsyPillowEntryService = etsyPillowEntryService;
    }

    //@Scheduled(cron = "10 11 22 * * ?")
    public void etsyRugEntry() {
        System.out.println(
                "Cron -" + System.currentTimeMillis() / 1000);
    }

    //@Scheduled(fixedDelay = 1000 * 3)
    public void scheduleChairishRugEntry() throws InterruptedException {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Chairish rug entry job is started.");
        this.chairishRugEntryService.execute();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Chairish rug entry job is finished.");
    }

    //@Scheduled(fixedDelay = 1000 * 3)
    public void scheduleChairishPillowEntry() throws InterruptedException {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Chairish pillow entry job is started.");
        this.chairishPillowEntryService.execute();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Chairish pillow entry job is finished.");
    }

    //@Scheduled(fixedDelay = 1000 * 2)
    public void scheduleEtsyRugEntry() throws InterruptedException {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is started.");
        this.etsyRugEntryService.execute();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is finished.");
    }

    //@Scheduled(fixedDelay = 1000 * 200)
    public void scheduleEtsyPillowEntry() throws InterruptedException {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is started.");
        this.etsyPillowEntryService.execute();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000 + " " + new Date() + " Etsy rug entry job is finished.");
    }

}
