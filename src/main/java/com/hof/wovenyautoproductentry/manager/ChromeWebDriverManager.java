package com.hof.wovenyautoproductentry.manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriverManager {

    private WebDriver chromeDriver;

    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // open Browser in maximized mode
        //options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        //options.addArguments("--headless");

        //options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("disable-infobars"); // disabling infobars
        //options.addArguments("--disable-extensions"); // disabling extensions
        //options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems

        this.chromeDriver = new ChromeDriver(options);
    }

    public void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
    }

    public WebDriver getChromeDriver() {
        setUp();
        return chromeDriver;
    }
}
