package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.configuration.properties.ChairishRugAuthenticationProperties;
import com.hof.wovenyautoproductentry.constants.ChairishConstants;
import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.SeleniumUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class ChairishRugEntryService {
    private static final String CHAIRISH_DASHBOARD_PAGE = "https://www.chairish.com/account/login";
    private static final String CHAIRISH_CREATION_PAGE = "https://www.chairish.com/product/create";
    private final String username;
    private final String password;
    private static final String HASAN_NEW_FOLDER = "3301-3400\\";
    private static final String CHAIRISH_EMAIL = "hetyemez@yahoo.com";
    private static final String CHAIRISH_PASSWORD = "etyemez57";
    private static final String HASAN_LOCAL_PATH = "C:\\Users\\hasanemre.ari\\Desktop\\Java\\chairish_photos\\";

    private final ProductRepository productRepository;

    public ChairishRugEntryService(ProductRepository productRepository,
                                   ChairishRugAuthenticationProperties properties) {
        this.productRepository = productRepository;
        username = properties.getUsername();
        password = properties.getPassword();
    }

    public void execute() throws InterruptedException {

        WebDriver driver = SeleniumUtils.openChrome(CHAIRISH_CREATION_PAGE);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Login
//        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_email\"]", CHAIRISH_EMAIL);
//        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_password\"]", CHAIRISH_PASSWORD);
//        SeleniumUtils.clickElement(driver, "//*[@id=\"content\"]/form/fieldset/div[3]/button");
//        //WebDriverWait wait = new WebDriverWait(driver, 20);
//        //wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,"//*[@id=\"shop-mgr-button\"]")));
//        Thread.sleep(3000);
//        //Go to creation page
//        SeleniumUtils.openUrl(driver, CHAIRISH_CREATION_PAGE);
//        SeleniumUtils.clickElement(driver, "//*[@id=\"content\"]/div/div/div[2]/span[2]/a");

        List<Product> products = productRepository.findAllBySkuNumberGreaterThanAndSkuNumberIsLessThanAndIsUploadedChairish("4550", "4650", false);

        for (Product product : products) {
            if (product.getQuantity() != 0) {
                try {

                    //////////Open create page
                    SeleniumUtils.openUrl(driver, CHAIRISH_CREATION_PAGE);
                    System.out.println(product.getSkuNumber());
                    int index = products.indexOf(product);
                    //////////Title
                    List<String> fields = Arrays.asList(product.getName().split("-"));
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_title\"]", fields.get(0));//Todo daha doğru parçalama
                    if (index != 0) {
                        Product previousProduct = products.get(index - 1);
                        previousProduct.setUploadedChairish(true);
                        productRepository.save(previousProduct);
                    }
                    //////////Category
                    setAttribute(js, driver, "//*[@id=\"js-basic-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu");
                    //Select Rugs Checkbox
                    Thread.sleep(2000);
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div/ul[2]/li[8]/div");
                    setAttribute(js, driver, "//*[@id=\"js-basic-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu hidden");

                    //////////Photo
                    List<String> images = new ArrayList<>();
                    images.add(product.getMainImageUrl());
                    images.addAll(product.getAdditionalImagePaths());
                    images.forEach(this::downloadImage);
                    Thread.sleep(400 * images.size());
                    //List<String> imagesPaths = generateImagePaths(product.getSkuNumber(), product.getAdditionalImagePaths().size());

                    IntStream.range(0, images.size())
                            .forEach(i -> {
                                String fileNameOfImage = getFileNameOfImage(images.get(i));
                                String imageXpath = "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[" + (i + 1) + "]/div/label/input";
                                SeleniumUtils.sendKeysToElement(driver, imageXpath, HASAN_LOCAL_PATH + fileNameOfImage);
                                //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[" + (i + 1) + "]/label/input", imagesPaths.get(i));
                                //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[1]/label/input", imagesPaths.get(0));

                            });


                    //////////Description
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_description\"]", product.getMetaDescription());
                    //Unknown Checkbox
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[1]/div/div[1]/label[2]/span[1]");
                    Thread.sleep(1000);
                    //////////Styles

                    for (String style : product.getStyles()) {
                        String styleFromMap = ChairishConstants.styleMap.get(style);
                        if (Objects.nonNull(styleFromMap)) {
                            setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu");
                            Thread.sleep(1000);
                            selectStyle(driver, style);
                            setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu hidden");
                        } else {
                            setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu");
                            Thread.sleep(1000);
                            selectStyle(driver, "Traditional");
                            setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[2]/div[1]/div[1]/div", "class", "menu js-menu hidden");
                        }
                    }

                    //////////Rug Construction
                    String weave = product.getWeave().name().equals("Handknotted") ? "Hand Knotted" : "Flatweave";
                    SeleniumUtils.selectFromElementByName(driver, "rug_construction", weave);
                    Thread.sleep(1000);
                    //////////Materials
                    SeleniumUtils.clickElement(driver, "//*[@id=\"id_materials\"]");
                    for (String material : product.getMaterials()) {
                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div", "class", "menu js-menu");
                        Thread.sleep(1500);
                        selectMaterial(driver, material);
                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div", "class", "menu js-menu hidden");
                    }

                    //////////Dominant Color
                    ArrayList<String> colors = new ArrayList<>(product.getColors());
                    if (colors.contains("Multicolor")) {
                        colors.remove("Multicolor");
                        colors.add("Red");
                        colors.add("Blue");
                        colors.add("Green");
                    }
                    IntStream.range(0, colors.size())
                            .forEach(i -> {
                                try {
                                    if (i == 0) {
                                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[5]/div[1]/div[1]/div", "class", "menu js-menu");
                                        Thread.sleep(500);
                                        selectColorForDomain(driver, colors.get(i));
                                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[5]/div[1]/div[1]/div", "class", "menu js-menu hidden");
                                    } else {
                                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[6]/div[1]/div[1]/div", "class", "menu js-menu");
                                        Thread.sleep(500);
                                        selectColorForAdditional(driver, colors.get(i));
                                        setAttribute(js, driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[6]/div[1]/div[1]/div", "class", "menu js-menu hidden");
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                    //////////Pattern
                    SeleniumUtils.selectFromElementByName(driver, "pattern", ChairishConstants.patternMap.get(product.getStyles().iterator().next()));
                    //////////Place of Origin
                    SeleniumUtils.selectFromElement(driver, "//*[@id=\"id_origin_region\"]", ChairishConstants.origin);
                    String length = getLengthByInch(product);//product.getLengthByInches().replace("`", ".").replace("\"", "").replace(" ", "").trim();
                    String width = getWidthByInch(product);//product.getWidthByInches().replace("`", ".").replace("\"", "").replace(" ", "").trim();
                    //////////Width
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_width\"]", width);
                    //////////Depth
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_depth\"]", ChairishConstants.depth);
                    //////////Height
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_height\"]", length);
                    //////////Period Made
                    SeleniumUtils.selectFromElementByName(driver, "period_made", ChairishConstants.periodMade);
                    //////////Used code
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[5]/ul/li[1]/label/span[1]");
                    //////////Used code
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[6]/ul/li[1]/label/span[1]");
                    //////////Used code
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[7]/ul/li[1]/label/span[1]");
                    //////////Condition Detail
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_condition_notes\"]", ChairishConstants.conditionNote);
                    //////////SKU Num
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_sku\"]", product.getSkuNumber());
                    //////////Price
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_price\"]", multiplyPrice(product.getPrice(), 1.2));
                    //////////Reserve Price
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_reserve_price\"]", multiplyPrice(product.getPrice(), 1.2));
                    //////////Arrange my own shipping
                    //SeleniumUtils.sendKeysToElementWithSubmitSpace(driver,"//*[@id=\"id_should_chairish_handle_shipping_1\"]","");
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-logistics-fields\"]/div[2]/div[5]/fieldset/div[1]/ul/li[2]/div/label/span[1]");
                    Thread.sleep(1000);
                    //////////I will charge for shipping
                    //SeleniumUtils.sendKeysToElementWithSubmitSpace(driver,"//*[@id=\"id_is_shipping_free_1\"]","");
                    SeleniumUtils.clickElement(driver, "//*[@id=\"js-seller-delegated-shipping-options\"]/div/ul/li[2]/div/label/span[1]");
                    Thread.sleep(1000);
                    //////////Shipping Charge
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_seller_delegated_shipping_charge\"]", ChairishConstants.shippingPriceRug);
                    //Todo Uncomment when go live
                    //////////Submit Button
                    Thread.sleep(5000);
                    SeleniumUtils.clickElement(driver, "//*[@id=\"content\"]/form/div[7]/fieldset/div/div[1]/button[2]");
                    // if (index == products.size()) {
                    Product lastProduct = products.get(products.size() - 1);
                    if (product.getSkuNumber().equals(lastProduct.getSkuNumber())) {
                        product.setUploadedChairish(true);
                        productRepository.save(product);
                        System.out.println("Sku number:" + product.getSkuNumber() + "entered.");
                    }
                    Thread.sleep(7000);
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("window.open()");
                    System.out.println(product.getSkuNumber() + "'da hata!");
                    System.out.println(e);
                    return;
                }
            }
        }
        System.out.println("Program bitti.");
    }

    private void selectStyle(WebDriver driver, String style) throws InterruptedException {
        String styleFromMap = ChairishConstants.styleMap.get(style);
        String styleXpath = ChairishConstants.styleMapXpath.get(styleFromMap);
        SeleniumUtils.clickElement(driver, styleXpath);

    }

    private void selectMaterial(WebDriver driver, String material) throws InterruptedException {
        String materialFromMap = ChairishConstants.materialMap.get(material);
        String materialXpath = ChairishConstants.materialMapXpath.get(materialFromMap);
        switch (materialFromMap) {
            case "Goat Skin":
            case "hair":
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div/ul[2]/li[1]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, materialXpath);
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div/ul[2]/li[1]/div[1]");
                break;
            default:
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div/ul[2]/li[20]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, materialXpath);
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[4]/div[1]/div[1]/div/ul[2]/li[20]/div[1]");
        }
    }

    private void selectColorForDomain(WebDriver driver, String color) throws InterruptedException {
        String colorFromMap = ChairishConstants.colorMap.get(color);
        String colorXpath = ChairishConstants.colorMapXpathForDomain.get(colorFromMap);
        switch (colorFromMap) {
            case "Beige":
            case "Khaki":
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[5]/div[1]/div[1]/div/ul[2]/li[13]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
                break;
            case "Cream":
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[5]/div[1]/div[1]/div/ul[2]/li[16]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
                break;
//            case "Multicolor":
//                colorXpath = ChairishConstants.colorMapXpathForDomain.get("Red");
//                Thread.sleep(1000);
//                SeleniumUtils.clickElement(driver, colorXpath);
//                colorXpath = ChairishConstants.colorMapXpathForDomain.get("Green");
//                Thread.sleep(1000);
//                SeleniumUtils.clickElement(driver, colorXpath);
//                colorXpath = ChairishConstants.colorMapXpathForDomain.get("Blue");
//                Thread.sleep(1000);
//                SeleniumUtils.clickElement(driver, colorXpath);
            default:
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
        }
    }

    private void selectColorForAdditional(WebDriver driver, String color) throws InterruptedException {
        String colorFromMap = ChairishConstants.colorMap.get(color);
        String colorXpath = ChairishConstants.colorMapXpathForAdditional.get(colorFromMap);
        switch (colorFromMap) {
            case "Beige":
            case "Khaki":
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[6]/div[1]/div[1]/div/ul[2]/li[13]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
                break;
            case "Cream":
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[6]/div[1]/div[1]/div/ul[2]/li[16]/div[1]");
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
                break;
            default:
                Thread.sleep(1000);
                SeleniumUtils.clickElement(driver, colorXpath);
        }
    }


    private String multiplyPrice(Integer price, double multiplier) {
        Double d = price * multiplier;
        Integer priceAsInteger = d.intValue();
        return priceAsInteger.toString();
    }

    private String getLengthByInch(Product product) {
        if (product.getLengthByInches().equals(StringUtils.EMPTY)) {
            return cmToInch(product.getLengthByCm());
        } else {
            return feetToInch(product.getLengthByInches());
        }
    }

    private String getWidthByInch(Product product) {
        if (product.getWidthByInches().equals(StringUtils.EMPTY)) {
            return cmToInch(product.getWidthByCm());
        } else {
            return feetToInch(product.getWidthByInches());
        }
    }

    private String cmToInch(int cm) {
        Double d = cm / 2.54;
        Integer inch = d.intValue();
        return inch.toString();
    }

    private String feetToInch(String feet) {
        List<String> fields = Arrays.asList(feet.split("`"));
        String firstPart = fields.get(0).replace(" ", "").trim();
        String secondPart = fields.get(1).replace(" ", "").replace("\"", "").trim();
        Integer inch = Integer.parseInt(firstPart) * 12 + Integer.parseInt(secondPart);
        return inch.toString();
    }

    private void downloadImage(String source) {
        String fileName = getFileNameOfImage(source);
        String localPath = HASAN_LOCAL_PATH + fileName;
        try {
            FileUtils.copyURLToFile(
                    new URL(source),
                    new File(localPath),
                    3000,
                    10000);
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getFileNameOfImage(String imagePath) {
        List<String> fields = Arrays.asList(imagePath.split("/"));
        return fields.get(fields.size() - 1);
    }
    public void setAttribute(JavascriptExecutor js,WebDriver driver, String xpath, String attName, String attValue) {
        WebElement element = SeleniumUtils.findElementByXpath(driver,xpath);
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attName, attValue);
    }
//    private List<String> generateImagePaths(String skuNumber, int additionalImageNumber) {
//        String imageSuffix = ".jpg";
//        List<String> alphabet = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
//        List<String> imagePathList = new ArrayList<>();
//        StringBuilder imagePaths = new StringBuilder();
//        imagePaths.append(HASAN_LOCAL_PATH).append(HASAN_NEW_FOLDER).
//                append(skuNumber).append(imageSuffix);
//        imagePathList.add(imagePaths.toString());
//        IntStream.range(0, additionalImageNumber)
//                .forEach(i -> {
////                    imagePaths.append(op).append(HASAN_LOCAL_PATH ).append(HASAN_NEW_FOLDER).
////                            append(skuNumber).append(alphabet.get(i)).append(imageSuffix).append(op).append(StringUtils.SPACE).toString();
//                    StringBuilder imagePath = new StringBuilder();
//                    imagePathList.add(imagePath.append(HASAN_LOCAL_PATH).append(HASAN_NEW_FOLDER).
//                            append(skuNumber).append(alphabet.get(i)).append(imageSuffix).toString());
//                });
//        return imagePathList;
//    }
}