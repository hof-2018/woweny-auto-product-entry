package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.LeafCategory;
import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.domain.product.ProductBuilder;
import com.hof.wovenyautoproductentry.domain.product.ProductStatus;
import com.hof.wovenyautoproductentry.domain.product.ProductType;
import com.hof.wovenyautoproductentry.domain.product.Weave;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hof.wovenyautoproductentry.constants.CsvHeaderConstants.*;

@Component
public class ProductMapper {

    private final ProductValidator productValidator;

    public ProductMapper(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    public Product csvRecordToProductEntity(CSVRecord record) {
        //TODO field naming(lengthfrom), underscore usage
        String skuNumber = trimFields(record.get(MODEL_HEADER));
        String name = trimFields(record.get(NAME));
        String category = trimFields(record.get(CATEGORY));
        String image = trimFields(record.get(IMAGE));
        String additionalImage = trimFields(record.get(ADDITIONAL_IMAGE));
        String price = trimFields(record.get(PRICE_HEADER));
        String quantity = trimFields(record.get(QUANTITY));
        String stockStatus = trimFields(record.get(STOCK_STATUS));
        String status = trimFields(record.get(STATUS));
        String lengthFromCm = trimFields(record.get(LENGTH));
        String widthFromCm = trimFields(record.get(WIDTH));
        String meta_keyword = trimFields(record.get(META_KEYWORD));
        String meta_title = trimFields(record.get(META_TITLE));
        String metaDescription = trimFields(record.get(META_DESCRIPTION));
        String dateAdded = trimFields(record.get(DATE_ADDED));
        String dateModified = trimFields(record.get(DATE_MODIFIED));
        String attributeAge = trimFields(record.get(ATTRIBUTE_AGE));
        String attributeColor = trimFields(record.get(ATTRIBUTE_COLOR));
        String attributeLength = trimFields(record.get(ATTRIBUTE_LENGTH));
        String attributeMaterial = trimFields(record.get(ATTRIBUTE_MATERIAL));
        String attributeRegion = trimFields(record.get(ATTRIBUTE_REGION));
        String attributeSize = trimFields(record.get(ATTRIBUTE_SIZE));
        String attributeStyle = trimFields(record.get(ATTRIBUTE_STYLE));
        String attributeWeave = trimFields(record.get(ATTRIBUTE_WEAVE));
        String attributeWidth = trimFields(record.get(ATTRIBUTE_WIDTH));

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

    private List<String> splitByTripleColon(String field) {
        //FIXME if empty check!
        if (Objects.equals(field, StringUtils.EMPTY)){
            return new ArrayList<>();
        }
        return Arrays.asList(field.split(":::"));
    }

    private List<String> splitByComma(String field) {
        //FIXME if empty check!
        if (Objects.equals(field, StringUtils.EMPTY)){
            return new ArrayList<>();
        }
        List<String> fields = Arrays.asList(field.split(","));
        return fields.stream().map(this::removeFirstCharacterIfItIsSpace).collect(Collectors.toList());
    }

    private String trimFields(String field) {
        field = field.replaceAll("[\\n\\r\\t]", "");
        return field;
    }

    private String removeFirstCharacterIfItIsSpace(String field) {
        return StringUtils.removeStart(field, " ");
    }

    private Integer getPureCm(String field){ // 247.00cm
        List<String> fields = Arrays.asList(field.split(".00cm"));
        return Integer.parseInt(fields.get(0));
    }

    private String getPureInch(String field){ // 9 ` 6 " (290 cm)
        List<String> fields = Arrays.asList(field.split(" \\("));
        return fields.get(0);
    }

    private Date stringToDate(String field){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        return formatter.parse(field);
        // todo
        } catch (ParseException e) {
            throw new IllegalStateException("Parsing error - string to date",e);
        }
    }

    private ProductType getProductType(String categoryPath){
        // --> RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Turkish Oushak:::RUGS
        // PILLOWS:::PILLOWS///16"x16"(40x40cm)
        //TODO sort by size get longest then manipulate
        List<String> categories = Arrays.asList(categoryPath.split("///"));
        List<String> subCategories = Arrays.asList(categories.get(0).split(":::"));
        return ProductType.productTypeFactory(subCategories.get(0));
    }

    private LeafCategory getLeafCategory (String categoryPath){
        // --> RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Turkish Oushak:::RUGS
        // PILLOWS:::PILLOWS///16"x16"(40x40cm)
        //TODO sort by size get longest then manipulate && trims space between words!!
        List<String> fields = Arrays.asList(categoryPath.split("///"));
        List<String> leaf = Arrays.asList(fields.get(fields.size()-1).split(":::"));
        return LeafCategory.findLeafCategoryValue(leaf.get(0));
    }
}
