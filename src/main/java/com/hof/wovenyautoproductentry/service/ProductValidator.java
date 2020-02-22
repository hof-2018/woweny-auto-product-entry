package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.domain.product.ProductType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.hof.wovenyautoproductentry.constants.PillowConstants.COVER;
import static com.hof.wovenyautoproductentry.constants.PillowConstants.CUSHION_COVER;
import static com.hof.wovenyautoproductentry.constants.PillowConstants.KILIM_COVER;
import static com.hof.wovenyautoproductentry.constants.PillowConstants.PILLOW;
import static com.hof.wovenyautoproductentry.constants.RugConstants.CARPET;
import static com.hof.wovenyautoproductentry.constants.RugConstants.DECORATIVE;
import static com.hof.wovenyautoproductentry.constants.RugConstants.HANDMADE;
import static com.hof.wovenyautoproductentry.constants.RugConstants.KILIM;
import static com.hof.wovenyautoproductentry.constants.RugConstants.RUG;

@Component
public class ProductValidator {


    public void validate(Product product) {
        validateMetaDataKeyword(product);
    }

    private void validateMetaDataKeyword(Product product) {
        if (product.getMetaKeyword().isEmpty()) {
            generateMetadataKeyword(product);
        }
    }

    public void generateMetadataKeyword(Product product) {
        if (product.getProductType().equals(ProductType.RUG)) {
            generateMetaDataKeywordForRug(product);
        } else {
            generateMetaDataKeywordForPillow(product);
        }
    }

    private void generateMetaDataKeywordForPillow(Product product) {
        Set<String> metadataKeywords = new HashSet<>();
        String inches = getInches(product.getLeafCategory());
        metadataKeywords.add(inches + StringUtils.SPACE + PILLOW);
        metadataKeywords.add(inches + StringUtils.SPACE + CUSHION_COVER);

        if (Objects.nonNull(product.getAge()) && !product.getAge().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(inches + " " + product.getAge() + StringUtils.SPACE + PILLOW);
        }

        if (Objects.nonNull(product.getWeave())) {
            metadataKeywords.add(product.getWeave() + StringUtils.SPACE + PILLOW);
        }
        if (Objects.nonNull(product.getRegion())) {
            metadataKeywords.add(inches + " " + product.getRegion() + StringUtils.SPACE + COVER);
        }
        product.getStyles().forEach(element -> {
            StringBuilder keyword = new StringBuilder();
            metadataKeywords.add(keyword.append(element).append(" ").append(PILLOW).toString());
        });

        product.getColors().forEach(element -> {
            StringBuilder keyword = new StringBuilder();
            metadataKeywords.add(keyword.append(element).append(" ").append(PILLOW).toString());
        });

        if (product.getWidthByCm() != 0 && product.getLengthByCm() != 0) {
            metadataKeywords.add(product.getWidthByCm() + "x" + product.getLengthByCm() + "cm " + CUSHION_COVER);
        }
        if (Objects.nonNull(product.getLeafCategory()) && !product.getLeafCategory().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(product.getLeafCategory().replace("\"", ""));
        }

        metadataKeywords.add(HANDMADE + StringUtils.SPACE + DECORATIVE + StringUtils.SPACE + PILLOW);
        metadataKeywords.add("Home Decor");
        metadataKeywords.add(inches + " " + DECORATIVE + StringUtils.SPACE + PILLOW);
        metadataKeywords.add(inches + " Turkish" + StringUtils.SPACE + KILIM_COVER + StringUtils.SPACE + CUSHION_COVER);
        metadataKeywords.add("Kilim Cush");
        metadataKeywords.add("Colorful Pillows");

        product.setMetaKeyword(metadataKeywords);
    }

    private String getInches(String leafCategory) {
        List<String> inches = Arrays.asList(leafCategory.split("\\("));
        return inches.get(0).replace("\"", "");
    }

    public void generateMetaDataKeywordForRug(Product product) {
        Set<String> metadataKeywords = new HashSet<>();
        String feet = "";
        if (!product.getWidthByInches().equals(StringUtils.EMPTY) && !product.getLengthByInches().equals(StringUtils.EMPTY)) {
            feet = getFirstNumberFromInches(product.getWidthByInches()) + "x" + getFirstNumberFromInches(product.getLengthByInches());
            metadataKeywords.add(feet + StringUtils.SPACE + RUG);
        }
        if (Objects.nonNull(product.getAge()) && !product.getAge().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(feet + " " + product.getAge() + StringUtils.SPACE + RUG);
        }
        if (Objects.nonNull(product.getSize()) && !product.getSize().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(product.getSize() + StringUtils.SPACE + CARPET);
        }
        if (Objects.nonNull(product.getWeave())) {
            metadataKeywords.add(product.getWeave() + StringUtils.SPACE + RUG);
        }
        if (Objects.nonNull(product.getRegion())) {
            metadataKeywords.add(feet + " " + product.getRegion() + StringUtils.SPACE + RUG);
        }
        product.getStyles().forEach(element -> {
            StringBuilder keyword = new StringBuilder();
            metadataKeywords.add(keyword.append(element).append(" ").append(KILIM).toString());
        });

        product.getColors().forEach(element -> {
            StringBuilder keyword = new StringBuilder();
            metadataKeywords.add(keyword.append(element).append(" ").append(KILIM).toString());
        });

        if (!product.getColors().isEmpty()) {
            metadataKeywords.add(feet + " " + getConcatStringFromSet(product.getColors()) + RUG);
        }

        if (product.getWidthByCm() != 0 && product.getLengthByCm() != 0) {
            metadataKeywords.add(product.getWidthByCm() + "x" + product.getLengthByCm() + StringUtils.SPACE + "cm " + CARPET);
        }
        if (Objects.nonNull(product.getLeafCategory()) &&  !product.getLeafCategory().equals(StringUtils.EMPTY)){
            metadataKeywords.add(product.getLeafCategory());
        }

        metadataKeywords.add(HANDMADE + StringUtils.SPACE + DECORATIVE + StringUtils.SPACE + RUG);
        metadataKeywords.add(feet + " " + DECORATIVE + StringUtils.SPACE + KILIM);
        metadataKeywords.add(feet + " Turkish" + StringUtils.SPACE + CARPET);

        product.setMetaKeyword(metadataKeywords);
    }

    public String getConcatStringFromSet(Set<String> elements) {
        StringBuilder result = new StringBuilder();
        elements.forEach(element -> result.append(element).append(StringUtils.SPACE));
        return result.toString();
    }

    public String getFirstNumberFromInches(String inches) {
        //10 ` 5 "
        List<String> strings = Arrays.asList(inches.split("`"));
        return strings.get(0).trim();
    }


}
