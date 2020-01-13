package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.util.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@Slf4j
public class EtsyRugEntryService {

    public void execute() throws InterruptedException {
        String ETSY_DASHBOARD_PAGE = "https://www.etsy.com/your/shops/me/dashboard?ref=mcpa";
        String ETSY_CREATION_PAGE = "https://www.etsy.com/your/shops/WovenHane/tools/listings/create";

        WebDriver driver = SeleniumUtils.openChrome(ETSY_DASHBOARD_PAGE);
        //Login
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"join_neu_email_field\"]", "hetyemez@yahoo.com", "");
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"join_neu_password_field\"]", "etyemez57", "");
        SeleniumUtils.clickElement(driver, "//*[@id=\"join-neu-form\"]/div[1]/div/div[5]/div/button");
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,"//*[@id=\"shop-mgr-button\"]")));
        Thread.sleep(3000);
        //Go to creation page
        SeleniumUtils.openUrl(driver, ETSY_CREATION_PAGE);

        //TODO read excel file

        Thread.sleep(2000);


        //////////Photo
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("51547.JPG");

        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"listing-edit-image-upload\"]", url.getPath(), "");
        //TODO Read photos from remote
        //sendKeysToElement(driver,"//*[@id=\"listing-edit-image-upload\"]","https://cdn03.ciceksepeti.com/cicek/at1677-1/XL/cicek-sepeti-100-kirmizi-gul-cicek-demeti-at1677-1-8d6463aee156183-4cb02247.jpg");

        //////////Title
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"title\"]", "Title Deneme", "");
        //////////Who Made
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"who_made\"]", "A member of my shop");
        //////////Is Supply
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"is_supply\"]", "A finished product");
        //////////When Made
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"when_made\"]", "2010 - 2019");
        //////////Category
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"taxonomy-search\"]", "Decorative Pillows", "E");
        Thread.sleep(1000);
        //////////Lenght
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"attribute-55-input\"]", "16", "");
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-55-unit\"]", "Inches");
        //////////Width
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"attribute-68-input\"]", "16", "");
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-68-unit\"]", "Inches");
        //////////Description
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"description\"]", "Description Deneme", "");
        //////////Section
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"sections\"]", "16\"x16\" (40x40cm)");
        //////////Tags
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"tags\"]", "Tag Deneme1,Tag Deneme2", "");
        SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[4]/div[24]/div/div/div[2]/div[1]/div[1]/div/div[2]/button");
        //////////Materials
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"materials\"]", "Material Deneme1,Material Deneme2", "");
        SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[4]/div[25]/div/div/div[2]/div[1]/div[1]/div/div[2]/button");
        //////////Price
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"price_retail-input\"]", "12345", "");
        //////////SKU Number
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"SKU-input\"]", "987654321", "");
        //////////Shipping
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[11]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div/div[1]/label/input", "", "S");
        //////////First Publish Button
        SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[3]/div/div[1]/div/div/div[2]/button[3]");
        Thread.sleep(10000);
        SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[3]/div/div[1]/div/div/div[2]/button[3]");
        //Todo Uncomment when go live
        //////////Second Publish Button
        //clickElement(driver, "//*[@id=\"overlay-region\"]/div/div[3]/button[1]");

        System.out.println("Program bitti.");

    }

}
