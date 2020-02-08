package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ChairishRugEntryService {
    private static final String CHAIRISH_DASHBOARD_PAGE = "https://www.chairish.com/account/login";
    private static final String CHAIRISH_CREATION_PAGE = "https://www.chairish.com/product/create";
    private static final String CHAIRISH_EMAIL = "hetyemez@yahoo.com";
    private static final String CHAIRISH_PASSWORD = "etyemez57";
    private static final String HASAN_LOCAL_PATH = "C:\\Users\\hasanemre.ari\\Desktop\\";
    private static final String HASAN_NEW_FOLDER = "3301-3400\\";

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

        //////////Title
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_title\"]", "Title Deneme");
        //////////Category
        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_categories\"]", "rug");
        Thread.sleep(1000);
        //////////Photo
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("51547.JPG");
        //SeleniumUtils.sendKeysToElementWithSubmitEnter(driver,"//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label","");
        List<String> imagesPaths = generateImagePaths("3301", 3);

        IntStream.range(0, imagesPaths.size())
                .forEach(i -> {
                    SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[" + (i + 1) + "]/label/input", imagesPaths.get(i));
                });
        //SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label", url.getPath());
        //TODO resim için son divde 1'den 12'ye kadar loop edip gelen resimlerin yüklenmesi
        //"//*[@id=\"js-basic-fields\"]/div[3]/div[2]/fieldset/div/div/div[2]/div[12]/label/input"
        //TODO OR read photos from remote
        //SeleniumUtils.sendKeysToElement(driver,"//*[@id=\"js-basic-fields\"]/div[2]/div[2]/fieldset/div/div/div[2]/div[1]/label/input",//*[@id=\\\"js-basic-fields\\\"]/div[3]/div[2]/fieldset/div/div/div[2]/div[1]/label/input",
        //"https://www.woveny.com/image/catalog/0521.jpg");

        //////////Description
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_description\"]", "Description Deneme");
        //////////First Next Button
//        SeleniumUtils.clickElement(driver,"//*[@id=\"js-basic-fields\"]/div[3]/div[3]/fieldset/div[2]/button");
//        Thread.sleep(2000);
        //Unknown Checkbox
        //SeleniumUtils.sendKeysToElementWithSubmitSpace(driver,"//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[1]/div/div[1]/label[2]/span[1]/input","");
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/fieldset/div[1]/div/div[1]/label[2]/span[1]");
        //////////Styles
        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_styles\"]", "mid-Century");
        //////////Rug Construction
        SeleniumUtils.selectFromElementByName(driver, "rug_construction", "Hand Knotted");
        //////////Materials
        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_materials\"]", "Bone");
        //////////Dominant Color
        SeleniumUtils.sendKeysToElementWithSubmitDownEnter(driver, "//*[@id=\"id_primary_color_code\"]", "red");
        //////////Pattern
        SeleniumUtils.selectFromElementByName(driver, "pattern", "Geometric");
        //////////Place of Origin
        SeleniumUtils.selectFromElement(driver, "//*[@id=\"id_origin_region\"]", "Turkey");
        //////////Width
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_width\"]", "16");
        //////////Depth
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_depth\"]", "0.2");
        //////////Height
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_dimension_height\"]", "16");
        //////////Period Made
        SeleniumUtils.selectFromElementByName(driver, "period_made", "Mid 20th Century");
        //////////Used code
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[5]/ul/li[1]/label/span[1]");
        //////////Used code
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[6]/ul/li[1]/label/span[1]");
        //////////Used code
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-details-fields\"]/div[2]/div[3]/fieldset/div[7]/ul/li[1]/label/span[1]");
        //////////Condition Detail
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_condition_notes\"]", "In very good condition");
        //////////Second Next Button
        //SeleniumUtils.clickElement(driver,"//*[@id=\"js-details-fields\"]/div[3]/div[3]/fieldset/div[10]/button");
        //////////SKU Num
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_sku\"]", "In very good condition");
        //////////Price
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_price\"]", "12345");
        //////////Reserve Price
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_reserve_price\"]", "12345");
        //////////Arrange my own shipping
        //SeleniumUtils.sendKeysToElementWithSubmitSpace(driver,"//*[@id=\"id_should_chairish_handle_shipping_1\"]","");
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-logistics-fields\"]/div[2]/div[5]/fieldset/div[1]/ul/li[2]/div/label/span[1]");
        Thread.sleep(1000);
        //////////I will charge for shipping
        //SeleniumUtils.sendKeysToElementWithSubmitSpace(driver,"//*[@id=\"id_is_shipping_free_1\"]","");
        SeleniumUtils.clickElement(driver, "//*[@id=\"js-seller-delegated-shipping-options\"]/div/ul/li[2]/div/label/span[1]");
        Thread.sleep(1000);
        //////////Shipping Charge
        SeleniumUtils.sendKeysToElement(driver, "//*[@id=\"id_seller_delegated_shipping_charge\"]", "45");
        //Todo Uncomment when go live
        Thread.sleep(5000);
        //////////Submit Button
        //SeleniumUtils.clickElement(driver, "//*[@id=\"content\"]/form/div[7]/fieldset/div/div[1]/button[2]");

        System.out.println("Program bitti.");
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