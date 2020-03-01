package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.configuration.properties.EtsyPillowAuthenticationProperties;
import com.hof.wovenyautoproductentry.constants.EtsyConstants;
import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.domain.product.ProductStatus;
import com.hof.wovenyautoproductentry.domain.product.ProductType;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EtsyPillowEntryService {

    private final String username;
    private final String password;
    private static final String okan_local_image_path = "/Users/okan.yildirim/Documents/my-projects/woveny/images/";

    private final ProductRepository productRepository;

    public EtsyPillowEntryService(ProductRepository productRepository,
                                  EtsyPillowAuthenticationProperties properties) {
        this.productRepository = productRepository;
        username = properties.getUsername();
        password = properties.getPassword();
    }

    public void execute() throws InterruptedException {
        String ETSY_DASHBOARD_PAGE = "https://www.etsy.com/your/shops/me/dashboard?ref=mcpa";

        WebDriver driver = SeleniumUtils.openChrome(ETSY_DASHBOARD_PAGE);
        //Login
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"join_neu_email_field\"]", username);
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"join_neu_password_field\"]", password);
        SeleniumUtils.clickElement(driver, "//*[@id=\"join-neu-form\"]/div[1]/div/div[5]/div/button");
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,"//*[@id=\"shop-mgr-button\"]")));
        Thread.sleep(3000);
        //Go to creation page
        //SeleniumUtils.openUrl(driver, ETSY_CREATION_PAGE);

        SeleniumUtils.clickElement(driver, "//*[@id=\"root\"]/div/div[1]/div[3]/div/div[1]/div[2]/ul/li[3]/a");
        //Product product = productRepository.findBySkuNumber("3301");
        //List<Product> products = productRepository.getProductsForEtsyWithLimit(PageRequest.of(0,10));

        List<Product> products = productRepository.findByProductTypeAndQuantityAndStatusAndStockStatusNotAndIsUploadedEtsy(PageRequest.of(0, 1), ProductType.PILLOW, 1, ProductStatus.ACTIVE, "Out Of Stock", false);

        for (Product product : products) {
            Thread.sleep(3000);
            SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[1]/header/div[1]/div/div[3]/div/div/a");
            Thread.sleep(2000);
            //////////Photo
            List<String> images = new ArrayList<>();
            images.add(product.getMainImageUrl());
            images.addAll(product.getAdditionalImagePaths());
            images.forEach(this::downloadImage);
            Thread.sleep(400 * images.size());

            images.forEach(imagePath -> {
                String fileNameOfImage = getFileNameOfImage(imagePath);
                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"listing-edit-image-upload\"]", okan_local_image_path + fileNameOfImage);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            //////////Title
            String title = getTitle(product);
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"title\"]", title);
            //////////Who Made
            SeleniumUtils.selectFromElement(driver, "//*[@id=\"who_made\"]", "A member of my shop");
            //////////Is Supply
            SeleniumUtils.selectFromElement(driver, "//*[@id=\"is_supply\"]", "A finished product");
            //////////When Made
            SeleniumUtils.selectFromElement(driver, "//*[@id=\"when_made\"]", "2010 - 2019");
            //////////Category
            SeleniumUtils.sendKeysToElementWithSubmitEnter(driver, "//*[@id=\"taxonomy-search\"]", "Decorative Pillows");
            Thread.sleep(1500);
            ///////Main Color
            ArrayList<String> colors = new ArrayList<>(product.getColors());
            if (!colors.isEmpty()) {
                SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-2\"]", EtsyConstants.colorMap.get(colors.get(0)));
                ///////Secondary Color
                if (colors.size() > 1) {
                    SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-271\"]", EtsyConstants.colorMap.get(colors.get(1)));
                }
            }

            ///////Type
            SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-348\"]", "Cover only");


            if (!product.getLengthByInches().equals(StringUtils.EMPTY) && !product.getWidthByInches().equals(StringUtils.EMPTY)) {
                //////////Lenght
                String leafCategory = product.getLeafCategory();// 12"X24"(30X60CM) Pillows
                List<String> inches = Arrays.asList(leafCategory.split("\\("));
                List<String> wL = Arrays.asList(inches.get(0).split("X"));

                String length = wL.get(1).replace("\"", "").replace(" ", "").trim();
                String width = wL.get(0).replace("\"", "").replace(" ", "").trim();

                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"attribute-55-input\"]", length);
                SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-55-unit\"]", "Inches");
                //////////Width
                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"attribute-68-input\"]", width);
                SeleniumUtils.selectFromElement(driver, "//*[@id=\"attribute-68-unit\"]", "Inches");
            }

            //////////Description
            String description = generateDescription(product);
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"description\"]", description);
            //////////Section
            SeleniumUtils.selectFromElement(driver, "//*[@id=\"sections\"]", EtsyConstants.leafCategoryPillowMap.get(product.getLeafCategory()).replace(" ", ""));
            //////////Tags
            String tags = getTags(product);
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"tags\"]", tags);
            SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[4]/div[24]/div/div/div[2]/div[1]/div[1]/div/div[2]/button");
            //////////Materials
            String materials = getMaterials(product.getMaterials());
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"materials\"]", materials);
            SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[4]/div[25]/div/div/div[2]/div[1]/div[1]/div/div[2]/button");
            //////////Price
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"price_retail-input\"]", product.getPrice().toString());
            //////////SKU Number
            SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"SKU-input\"]", product.getSkuNumber());
            //////////Shipping
            SeleniumUtils.sendKeysToElementWithSubmitSpace(driver, "//*[@id=\"page-region\"]/div/div/div[2]/div/div/div/div[11]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div/div[1]/label/input");

            //////////First Publish Button
            SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[3]/div/div[1]/div/div/div[2]/button[3]");
            Thread.sleep(10000);
            SeleniumUtils.clickElement(driver, "//*[@id=\"page-region\"]/div/div/div[3]/div/div[1]/div/div/div[2]/button[3]");
            //////////Second Publish Button
            Thread.sleep(1000);
            //SeleniumUtils.clickElement(driver, "//*[@id=\"overlay-region\"]/div/div[3]/button[1]");

            product.setUploadedEtsy(true);
            productRepository.save(product);
            images.forEach(imagePath -> {
                String fileNameOfImage = getFileNameOfImage(imagePath);
                File fileToDelete = FileUtils.getFile(okan_local_image_path + fileNameOfImage);
                boolean success = FileUtils.deleteQuietly(fileToDelete);
            });
            System.out.println("product id: " + product.getId() + " skuNumber: " + product.getSkuNumber() + " is done");
        }
        driver.close();
    }

    private String getTitle(Product product) {
        String name = product.getName();
        String leafCategory = product.getLeafCategory();// 12"X24"(30X60CM) Pillows
        List<String> inches = Arrays.asList(leafCategory.split("\\("));
        return inches.get(0) + " " + name;
    }

    private void downloadImage(String source) {
        String fileName = getFileNameOfImage(source);
        String localPath = okan_local_image_path + fileName;
        try {
            FileUtils.copyURLToFile(
                    new URL(source),
                    new File(localPath),
                    3000,
                    10000);
            Thread.sleep(800);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getFileNameOfImage(String imagePath) {
        List<String> fields = Arrays.asList(imagePath.split("/"));
        return fields.get(fields.size() - 1);
    }

    private String getMaterials(Set<String> materials) {
        StringBuilder materialAsString = new StringBuilder();
        materials.forEach(material -> materialAsString.append(material).append(","));
        return StringUtils.chop(materialAsString.toString());
    }

    private String getTags(Product product) {
        StringBuilder tags = new StringBuilder();
        Set<String> metaKeyword = product.getMetaKeyword();
        metaKeyword.forEach(tag -> tags.append(tag).append(","));
        return tags.append("Rug").toString();
    }

    private String generateDescription(Product product) {
        StringBuilder description = new StringBuilder();
        if (product.getMetaDescription() == null || product.getMetaDescription().equals(StringUtils.EMPTY)) {
            description.append(product.getName());
        } else {
            description.append(product.getMetaDescription());
        }
        description.append(StringUtils.LF).append(StringUtils.LF);

        description.append(EtsyConstants.PILLOW_DESCRIPTION);
        description.append(StringUtils.LF);
        description.append(product.getSkuNumber());

        return description.toString();
    }
}
