package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.*;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

import static com.hof.wovenyautoproductentry.constants.CsvHeaderConstants.*;

@Component
public class AmaraProductMapper {

    public Product csvRecordToProductEntity(CSVRecord record) {
        //TODO field naming(lengthfrom), underscore usage
        String skuNumber = record.get(MODEL_HEADER);
        String name = record.get(NAME);
        String category = record.get(CATEGORY);
        String image = record.get(IMAGE);
        String additionalImage = record.get(ADDITIONAL_IMAGE);
        String price = record.get(PRICE_HEADER);
        String quantity = record.get(QUANTITY);
        String stockStatus = record.get(STOCK_STATUS);
        String status = record.get(STATUS);
        String lengthFromCm = record.get(LENGTH);
        String widthFromCm = record.get(WIDTH);
        String meta_keyword = record.get(META_KEYWORD);
        String meta_title = record.get(META_TITLE);
        String metaDescription = record.get(META_DESCRIPTION);
        String dateAdded = record.get(DATE_ADDED);
        String dateModified = record.get(DATE_MODIFIED);
        String attributeAge = record.get(ATTRIBUTE_AGE);
        String attributeColor = record.get(ATTRIBUTE_COLOR);
        String attributeLength = record.get(ATTRIBUTE_LENGTH);
        String attributeMaterial = record.get(ATTRIBUTE_MATERIAL);
        String attributeRegion = record.get(ATTRIBUTE_REGION);
        String attributeSize = record.get(ATTRIBUTE_SIZE);
        String attributeStyle = record.get(ATTRIBUTE_STYLE);
        String attributeWeave = record.get(ATTRIBUTE_WEAVE);
        String attributeWidth = record.get(ATTRIBUTE_WIDTH);

        List<String> additionalImages = splitByTripleColon(additionalImage);
        List<String> colors = splitByComma(attributeColor);
        List<String> materials = splitByComma(attributeMaterial);
        List<String> styles = splitByComma(attributeStyle);
        List<String> metaKeywords = splitByComma(meta_keyword);

        //RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Turkish Anatolian Rugs:::RUGS
        //RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Vintage Modern Kilims:::RUGS

        ProductType productType = getProductType("PILLOWS:::PILLOWS///16\"x16\"(40x40cm)");
        LeafCategory leafCategory = getLeafCategory("PILLOWS:::PILLOWS///16\"x16\"(40x40cm)");

        Product product = ProductBuilder.aProduct()
                .skuNumber(skuNumber)
                .name(name)
                .categoryPath(category)
                .mainImageUrl(image)
                .additionalImagePaths(additionalImages)
                .price(Integer.parseInt(price))
                .quantity(Integer.parseInt(quantity))
                .stockStatus(stockStatus)
                .status(Integer.parseInt(status) == 1 ? ProductStatus.ACTIVE : ProductStatus.PASSIVE)
                .lengthByCm(getPureCm(lengthFromCm))
                .widthByCm(getPureCm(widthFromCm))
                .lengthByInches(getPureInch(attributeLength))
                .widthByInches(getPureInch(attributeWidth))
                .metaKeyword(new HashSet<>(metaKeywords))
                .metaDescription(metaDescription)
                .wovenyCreateDate(stringToDate(dateAdded))
                .wovenyModifiedDate(stringToDate(dateModified))
                .age(attributeAge)
                .colors(new HashSet<>(colors))
                .materials(new HashSet<>(materials))
                .region(attributeRegion)
                .size(attributeSize)
                .styles(new HashSet<>(styles))
                .weave(Weave.weaveFactory(attributeWeave))
                .productType(getProductType(category))
                .leafCategory(getLeafCategory(category))
                .build();

        productValidator.validate(product);
        return product;
    }
}
