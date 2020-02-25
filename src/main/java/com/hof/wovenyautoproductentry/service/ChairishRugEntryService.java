package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.constants.ChairishConstants;
import com.hof.wovenyautoproductentry.constants.RugConstants;
import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.SeleniumUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class ChairishRugEntryService {
    private static final String CHAIRISH_DASHBOARD_PAGE = "https://www.chairish.com/account/login";
    private static final String CHAIRISH_CREATION_PAGE = "https://www.chairish.com/product/create";
    private static final String CHAIRISH_EMAIL = "hetyemez@yahoo.com";
    private static final String CHAIRISH_PASSWORD = "etyemez57";
    private static final String HASAN_LOCAL_PATH = "C:\\Users\\hasanemre.ari\\Desktop\\";
    private static final String HASAN_NEW_FOLDER = "3301-3400\\";

    private final ProductRepository productRepository;

    public ChairishRugEntryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute() throws InterruptedException {

        WebDriver driver = SeleniumUtils.openChrome(CHAIRISH_CREATION_PAGE);
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
//        //TODO read excel file

        //Thread.sleep(2000);

        List<Product> products = productRepository.findAllBySkuNumberGreaterThanAndSkuNumberIsLessThanAndIsUploadedChairish("3300", "3396", false);

        for (Product product : products) {

            try {

                //////////Open create page
                SeleniumUtils.openUrl(driver, CHAIRISH_CREATION_PAGE);

                //////////Title
                List<String> fields = Arrays.asList(product.getName().split("-"));
                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_title\"]", fields.get(0));//Todo daha doğru parçalama
                int index = products.indexOf(product);
                if (index != 0) {
                    Product previousProduct = products.get(index - 1);
                    previousProduct.setUploadedChairish(true);
                    productRepository.save(previousProduct);
                }
                //////////Category
                SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_categories\"]", RugConstants.RUG);
                Thread.sleep(1000);

                //////////Photo
                //SeleniumUtils.sendKeysToElementWithSubmitEnter(driver,"//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label","");
                List<String> imagesPaths = generateImagePaths(product.getSkuNumber(), product.getAdditionalImagePaths().size());

                IntStream.range(0, imagesPaths.size())
                        .forEach(i -> {
                            String imageXpath = "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[" + (i + 1) + "]/label/input";
                            SeleniumUtils.sendKeysToElement(driver, imageXpath, imagesPaths.get(i));
                            //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[" + (i + 1) + "]/label/input", imagesPaths.get(i));
                            //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[1]/fieldset/div/div/div[2]/div[1]/label/input", imagesPaths.get(0));

                        });
                //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label", url.getPath());
                //TODO OR read photos from remote
                //SeleniumUtils.sendKeysToElement(driver,"//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label/input",//*[@id=\\\"js-basic-fields\\\"]/div[3]/div[2]/fieldset/div/div/div[2]/div[1]/label/input",
                //"https://www.woveny.com/image/catalog/0521.jpg");

                //////////Description
                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_description\"]", product.getMetaDescription());
                //////////First Next Button
//        SeleniumUtils.clickElement(driver,"//*[@id=\"js-basic-fields\"]/div[3]/div[3]/fieldset/div[2]/button");
//        Thread.sleep(2000);
                //Unknown Checkbox
                SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[1]/div/div[1]/label[2]/span[1]");
                Thread.sleep(1000);
                //////////Styles

                for (String style : product.getStyles()) {
                    String styleFromMap = ChairishConstants.styleMap.get(style);
                    if (Objects.nonNull(styleFromMap)) {
                        SeleniumUtils.clickElement(driver, "//*[@id=\"id_styles\"]");
                        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_styles\"]", styleFromMap);
                    } else {
                        SeleniumUtils.clickElement(driver, "//*[@id=\"id_styles\"]");
                        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_styles\"]", "Traditional");
                    /*SeleniumUtils.clickElement(driver,"//*[@id=\"id_styles\"]");
                    SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_styles\"]", "Contemporary");
                    SeleniumUtils.clickElement(driver,"//*[@id=\"id_styles\"]");
                    SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_styles\"]", "Mid-Century Modern");*/
                    }
                }
                //////////Rug Construction
                String weave = product.getWeave().name().equals("Handknotted") ? "Hand Knotted" : "Flatweave";
                SeleniumUtils.selectFromElementByName(driver, "rug_construction", weave);
                //////////Materials
                SeleniumUtils.clickElement(driver,"//*[@id=\"id_materials\"]");
                for (String material : product.getMaterials()) {
                    String materialFromMap = ChairishConstants.materialMap.get(material);
                    SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_materials\"]", materialFromMap);
                }
                //////////Dominant Color
                SeleniumUtils.clickElement(driver,"//*[@id=\"id_primary_color_code\"]");
                ArrayList<String> colors = new ArrayList<>(product.getColors());
                IntStream.range(0, product.getColors().size())
                        .forEach(i -> {
                            try {
                                if (i == 0) {
                                    SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_primary_color_code\"]", ChairishConstants.colorMap.get(colors.get(i)));
                                } else {
                                    SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_colors\"]", ChairishConstants.colorMap.get(colors.get(i)));
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
                //////////Second Next Button
                //SeleniumUtils.clickElement(driver,"//*[@id=\"js-details-fields\"]/div[3]/div[3]/fieldset/div[10]/button");
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
                SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_seller_delegated_shipping_charge\"]", ChairishConstants.shippingPrice);
                //Todo Uncomment when go live
                //////////Submit Button
                Thread.sleep(5000);
                SeleniumUtils.clickElement(driver, "//*[@id=\"content\"]/form/div[7]/fieldset/div/div[1]/button[2]");

                Thread.sleep(7000);
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("window.open()");
                System.out.println(product.getSkuNumber() + "'da hata!");
                System.out.println(e);
                return;
            }
        }
        System.out.println("Program bitti.");
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
    private List<String> generateImagePaths(String skuNumber, int additionalImageNumber) {
        String imageSuffix = ".jpg";
        List<String> alphabet = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        List<String> imagePathList = new ArrayList<>();
        StringBuilder imagePaths = new StringBuilder();
        imagePaths.append(HASAN_LOCAL_PATH).append(HASAN_NEW_FOLDER).
                append(skuNumber).append(imageSuffix);
        imagePathList.add(imagePaths.toString());
        IntStream.range(0, additionalImageNumber)
                .forEach(i -> {
//                    imagePaths.append(op).append(HASAN_LOCAL_PATH ).append(HASAN_NEW_FOLDER).
//                            append(skuNumber).append(alphabet.get(i)).append(imageSuffix).append(op).append(StringUtils.SPACE).toString();
                    StringBuilder imagePath = new StringBuilder();
                    imagePathList.add(imagePath.append(HASAN_LOCAL_PATH).append(HASAN_NEW_FOLDER).
                            append(skuNumber).append(alphabet.get(i)).append(imageSuffix).toString());
                });
        return imagePathList;
    }
}