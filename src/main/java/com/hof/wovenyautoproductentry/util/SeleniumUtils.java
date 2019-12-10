package com.hof.wovenyautoproductentry.util;

import com.hof.wovenyautoproductentry.manager.ChromeWebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumUtils {

    public static WebDriver openChrome(String url) {
        ChromeWebDriverManager chromeWebDriverManager = new ChromeWebDriverManager();
        WebDriver driver = chromeWebDriverManager.getChromeDriver();
        driver.get(url);
        return  driver;
    }

    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static WebElement findElementByXpath(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static void clickElement(WebDriver driver, String xpath) {

        WebElement element = findElementByXpath(driver, xpath);
        element.click();
    }

    public static void sendKeysToElement(WebDriver driver, String xpath, String keys, String submit) throws InterruptedException {
        WebElement element = findElementByXpath(driver, xpath);
        element.sendKeys(keys);
        if (submit.equals("E")) {
            Thread.sleep(2000);
            element.sendKeys(Keys.ENTER);
        }
        if (submit.equals("S")) {
            Thread.sleep(2000);
            element.sendKeys(Keys.SPACE);
        }

    }

    public static void selectFromElement(WebDriver driver, String xpath, String Selection) {
        Select dropdown = new Select(findElementByXpath(driver, xpath));
        dropdown.selectByVisibleText(Selection);
    }




}
