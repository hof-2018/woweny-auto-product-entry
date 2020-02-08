package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.hof.wovenyautoproductentry.constants.RugConstants.CARPET;
import static com.hof.wovenyautoproductentry.constants.RugConstants.DECORATIVE;
import static com.hof.wovenyautoproductentry.constants.RugConstants.HANDMADE;
import static com.hof.wovenyautoproductentry.constants.RugConstants.RUG;

@Component
public class ProductValidator {


    public void validate(Product product) {
        validateMetaDataKeyword(product);
        System.out.println("");
    }

    private void validateMetaDataKeyword(Product product) {
        if (product.getMetaKeyword().isEmpty()) {
            generateMetadataKeyword(product);
        }
    }

    public void generateMetadataKeyword(Product product) {
        Set<String> metadataKeywords = new HashSet<>();
        if (Objects.nonNull(product.getAge()) && !product.getAge().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(product.getAge() + StringUtils.SPACE + RUG);
        }
        if (Objects.nonNull(product.getSize()) && !product.getSize().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(product.getSize() + StringUtils.SPACE + CARPET);
        }
        if (Objects.nonNull(product.getWeave())) {
            metadataKeywords.add(product.getWeave() + StringUtils.SPACE + RUG);
        }
        if (Objects.nonNull(product.getWeave())) {
            metadataKeywords.add(product.getRegion() + StringUtils.SPACE + RUG);
        }
        if (!product.getStyles().isEmpty()) {
            metadataKeywords.add(getConcatStringFromSet(product.getStyles()) + RUG);
        }
        if (!product.getColors().isEmpty()) {
            metadataKeywords.add(getConcatStringFromSet(product.getColors()) + RUG);
        }
        if (!product.getWidthByInches().equals(StringUtils.EMPTY) && !product.getLengthByInches().equals(StringUtils.EMPTY)) {
            metadataKeywords.add(product.getWidthByInches().replace(StringUtils.SPACE, StringUtils.EMPTY) + " x " +
                    product.getLengthByInches().replace(StringUtils.SPACE, StringUtils.EMPTY) + StringUtils.SPACE + RUG);
        }
        if (product.getWidthByCm() != 0 && product.getLengthByCm() != 0) {
            metadataKeywords.add(product.getWidthByCm() + " x " + product.getLengthByCm() + StringUtils.SPACE + CARPET);
        }

        metadataKeywords.add(HANDMADE + StringUtils.SPACE + DECORATIVE + StringUtils.SPACE + RUG);

        product.setMetaKeyword(metadataKeywords);
    }

    public String getConcatStringFromSet(Set<String> elements) {
        StringBuilder result = new StringBuilder();
        elements.forEach(element -> result.append(element).append(StringUtils.SPACE));
        return result.toString();
    }
}
