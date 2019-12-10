package com.hof.wovenyautoproductentry.scheduler;

import com.hof.wovenyautoproductentry.manager.ChromeWebDriverManager;
import org.openqa.selenium.WebDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerController {

    private static final String CHROMEDRIVER_EXE = "chromedriver";

    @Scheduled(cron = "10 11 22 * * ?")
    public void etsyRugEntry(){
        System.out.println(
                "Cron -" + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000*50)
    public void scheduleFixedRateTask() {
        ChromeWebDriverManager chromeWebDriverManager = new ChromeWebDriverManager();
        WebDriver driver = chromeWebDriverManager.getChromeDriver();
        driver.get("https://www.etsy.com/your/shops/me/dashboard?ref=mcpa");
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

}
