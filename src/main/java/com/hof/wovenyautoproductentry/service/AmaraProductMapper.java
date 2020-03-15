package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.AmaraProduct;
import com.hof.wovenyautoproductentry.domain.product.AmaraProductBuilder;
import com.hof.wovenyautoproductentry.domain.product.AmaraProductStatus;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.hof.wovenyautoproductentry.constants.AmaraCsvHeaderConstants.*;

@Component
public class AmaraProductMapper {

    private static final NumberFormat turkishNumberFormat = NumberFormat.getInstance(Locale.FRANCE);

    public AmaraProduct csvRecordToProductEntity(CSVRecord record) {

        String skuNumber = record.get(SKU_NUMBER);
        String name = record.get(NAME);
        String metaDescription = record.get(META_DESCRIPTION);
        String productStyles1 = record.get(PRODUCT_STYLES1);
        String productStyles2 = record.get(PRODUCT_STYLES2);
        String productStyles3 = record.get(PRODUCT_STYLES3);
        String weave = record.get(WEAVE);
        String productMetarials1 = record.get(PRODUCT_METARIALS1);
        String productMetarials2 = record.get(PRODUCT_METARIALS2);
        String productColors1 = record.get(PRODUCT_COLORS1);
        String productColors2 = record.get(PRODUCT_COLORS2);
        String productColors3 = record.get(PRODUCT_COLORS3);
        String productColors4 = record.get(PRODUCT_COLORS4);
        String pattern = record.get(PATTERN);
        String region = record.get(REGION);
        String widthByFeetF = record.get(WIDTH_BY_FEET_F);
        String widthByFeetI = record.get(WIDTH_BY_FEET_I);
        String widthByInches = record.get(WIDTH_BY_INCHES);
        String lengthByFeetF = record.get(LENGTH_BY_FEET_F);
        String lengthByFeetI = record.get(LENGTH_BY_FEET_I);
        String lengthByInches = record.get(LENGTH_BY_INCHES);
        String height = record.get(HEIGHT);
        String age = record.get(AGE);
        String periodMade = record.get(PERIOD_MADE);
        String isUsed = record.get(IS_USED);
        String hasImperfection = record.get(HAS_IMPERFECTION);
        String conditionDetails = record.get(CONDITION_DETAILS);
        String price = record.get(PRICE);
        String discount = record.get(DISCOUNT);
        String retailPrice = record.get(RETAIL_PRICE);
        String isLocalPickup = record.get(IS_LOCAL_PICKUP);
        String localDeliveryMiles = record.get(LOCAL_DELIVERY_MILES);
        String pickupCharge = record.get(PICKUP_CHARGE);
        String isOwnShipping = record.get(IS_OWN_SHIPPING);
        String isFreeShipping = record.get(IS_FREE_SHIPPING);
        String status = record.get(STATUS);

        Double doubleHeight = null;
        Double doubleDiscount = null;
        Integer integerPickupCharge = null;
        try {
            doubleHeight = isNotNullAndEmpty(height) ? turkishNumberFormat.parse(height).doubleValue() : null;
            doubleDiscount = isNotNullAndEmpty(discount) ? turkishNumberFormat.parse(discount).doubleValue() : null;
            integerPickupCharge = isNotNullAndEmpty(pickupCharge) ? turkishNumberFormat.parse(pickupCharge).intValue() : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return AmaraProductBuilder.anAmaraProduct()
                .skuNumber(skuNumber)
                .name(name)
                .metaDescription(metaDescription)
                .styles(createListIfElementsExists(productStyles1, productStyles2, productStyles3))
                .weave(weave)
                .materials(createListIfElementsExists(productMetarials1, productMetarials2))
                .colors(createListIfElementsExists(productColors1, productColors2, productColors3, productColors4))
                .pattern(pattern)
                .region(region)
                .widthByFInches(createFeetInchesString(widthByFeetF, widthByFeetI))
                .widthByInches(isNotNullAndEmpty(widthByInches) ? Integer.valueOf(widthByInches) : null)
                .lengthByFInches(createFeetInchesString(lengthByFeetF, lengthByFeetI))
                .lengthByInches(isNotNullAndEmpty(lengthByInches) ? Integer.valueOf(lengthByInches) : null)
                .height(doubleHeight)
                .age(age)
                .periodMade(periodMade)
                .isUsed(isChecked(isUsed))
                .hasImperfection(isChecked(hasImperfection))
                .conditionDetails(conditionDetails)
                .price(isNotNullAndEmpty(price) ? Integer.valueOf(price) : null)
                .discount(doubleDiscount)
                .retailPrice(isNotNullAndEmpty(retailPrice) ? Integer.valueOf(retailPrice) : null)
                .isLocalPickup(isChecked(isLocalPickup))
                .localDeliveryMiles(isNotNullAndEmpty(localDeliveryMiles) ? Integer.valueOf(localDeliveryMiles) : null)
                .pickupCharge(integerPickupCharge)
                .isOwnShipping(isChecked(isOwnShipping))
                .isFreeShipping(isChecked(isFreeShipping))
                .status(AmaraProductStatus.productStatus(status))
                .build();
    }

    private List<String> createListIfElementsExists(String... elements) {
        List<String> elementList = Arrays.stream(elements).filter(element -> !element.isBlank()).collect(Collectors.toList());
        return elementList.isEmpty() ? null : elementList;
    }

    private String createFeetInchesString(String feet, String inches) {
        return feet + " ` " + inches + " \"";
    }

    private Boolean isChecked(String element) {
        return element != null && !element.isBlank() && element.contains("x");
    }

    private Boolean isNotNullAndEmpty(String element) {
        return element != null && !element.isBlank();
    }
}
