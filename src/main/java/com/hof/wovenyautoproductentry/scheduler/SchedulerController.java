package com.hof.wovenyautoproductentry.scheduler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;

@Component
public class SchedulerController {

    private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
    protected WebDriver driver;

    @Scheduled(cron = "10 11 22 * * ?")
    public void etsyRugEntry(){

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.etsy.com/your/shops/me/dashboard?ref=mcpa");
        System.out.println(
                "Cron -" + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    public void setUp() {
        String driverFile = findFile();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverFile))
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("--headless");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.merge(capabilities);
        this.driver = new ChromeDriver(service, options);
    }

    private String findFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(CHROMEDRIVER_EXE);
        return url.getFile();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
