package com.hof.wovenyautoproductentry.domain.product;

import java.util.ArrayList;
import java.util.List;

public final class AmaraProductBuilder {
    private Long id;
    private String skuNumber;
    private String name;
    private String metaDescription;
    private List<String> styles = new ArrayList<>();
    private String weave;
    private List<String> materials = new ArrayList<>();
    private List<String> colors = new ArrayList<>();
    private String pattern;
    private String region;
    private String widthByFInches;
    private Integer widthByInches;
    private String lengthByFInches;
    private Integer lengthByInches;
    private Double height;
    private String age;
    private String periodMade;
    private Boolean isUsed;
    private Boolean hasImperfection;
    private String conditionDetails;
    private Integer price;
    private Double discount;
    private Integer retailPrice;
    private Boolean isLocalPickup;
    private Integer localDeliveryMiles;
    private Integer pickupCharge;
    private Boolean isOwnShipping;
    private Boolean isFreeShipping;
    private AmaraProductStatus status;

    private AmaraProductBuilder() {
    }

    public static AmaraProductBuilder anAmaraProduct() {
        return new AmaraProductBuilder();
    }

    public AmaraProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AmaraProductBuilder skuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
        return this;
    }

    public AmaraProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AmaraProductBuilder metaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
        return this;
    }

    public AmaraProductBuilder styles(List<String> styles) {
        this.styles = styles;
        return this;
    }

    public AmaraProductBuilder weave(String weave) {
        this.weave = weave;
        return this;
    }

    public AmaraProductBuilder materials(List<String> materials) {
        this.materials = materials;
        return this;
    }

    public AmaraProductBuilder colors(List<String> colors) {
        this.colors = colors;
        return this;
    }

    public AmaraProductBuilder pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public AmaraProductBuilder region(String region) {
        this.region = region;
        return this;
    }

    public AmaraProductBuilder widthByFInches(String widthByFInches) {
        this.widthByFInches = widthByFInches;
        return this;
    }

    public AmaraProductBuilder widthByInches(Integer widthByInches) {
        this.widthByInches = widthByInches;
        return this;
    }

    public AmaraProductBuilder lengthByFInches(String lengthByFInches) {
        this.lengthByFInches = lengthByFInches;
        return this;
    }

    public AmaraProductBuilder lengthByInches(Integer lengthByInches) {
        this.lengthByInches = lengthByInches;
        return this;
    }

    public AmaraProductBuilder height(Double height) {
        this.height = height;
        return this;
    }

    public AmaraProductBuilder age(String age) {
        this.age = age;
        return this;
    }

    public AmaraProductBuilder periodMade(String periodMade) {
        this.periodMade = periodMade;
        return this;
    }

    public AmaraProductBuilder isUsed(Boolean isUsed) {
        this.isUsed = isUsed;
        return this;
    }

    public AmaraProductBuilder hasImperfection(Boolean hasImperfection) {
        this.hasImperfection = hasImperfection;
        return this;
    }

    public AmaraProductBuilder conditionDetails(String conditionDetails) {
        this.conditionDetails = conditionDetails;
        return this;
    }

    public AmaraProductBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    public AmaraProductBuilder discount(Double discount) {
        this.discount = discount;
        return this;
    }

    public AmaraProductBuilder retailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
        return this;
    }

    public AmaraProductBuilder isLocalPickup(Boolean isLocalPickup) {
        this.isLocalPickup = isLocalPickup;
        return this;
    }

    public AmaraProductBuilder localDeliveryMiles(Integer localDeliveryMiles) {
        this.localDeliveryMiles = localDeliveryMiles;
        return this;
    }

    public AmaraProductBuilder pickupCharge(Integer pickupCharge) {
        this.pickupCharge = pickupCharge;
        return this;
    }

    public AmaraProductBuilder isOwnShipping(Boolean isOwnShipping) {
        this.isOwnShipping = isOwnShipping;
        return this;
    }

    public AmaraProductBuilder isFreeShipping(Boolean isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
        return this;
    }

    public AmaraProductBuilder status(AmaraProductStatus status) {
        this.status = status;
        return this;
    }

    public AmaraProduct build() {
        AmaraProduct amaraProduct = new AmaraProduct();
        amaraProduct.setId(id);
        amaraProduct.setSkuNumber(skuNumber);
        amaraProduct.setName(name);
        amaraProduct.setMetaDescription(metaDescription);
        amaraProduct.setStyles(styles);
        amaraProduct.setWeave(weave);
        amaraProduct.setMaterials(materials);
        amaraProduct.setColors(colors);
        amaraProduct.setPattern(pattern);
        amaraProduct.setRegion(region);
        amaraProduct.setWidthByFInches(widthByFInches);
        amaraProduct.setWidthByInches(widthByInches);
        amaraProduct.setLengthByFInches(lengthByFInches);
        amaraProduct.setLengthByInches(lengthByInches);
        amaraProduct.setHeight(height);
        amaraProduct.setAge(age);
        amaraProduct.setPeriodMade(periodMade);
        amaraProduct.setUsed(isUsed);
        amaraProduct.setHasImperfection(hasImperfection);
        amaraProduct.setConditionDetails(conditionDetails);
        amaraProduct.setPrice(price);
        amaraProduct.setDiscount(discount);
        amaraProduct.setRetailPrice(retailPrice);
        amaraProduct.setLocalPickup(isLocalPickup);
        amaraProduct.setLocalDeliveryMiles(localDeliveryMiles);
        amaraProduct.setPickupCharge(pickupCharge);
        amaraProduct.setOwnShipping(isOwnShipping);
        amaraProduct.setFreeShipping(isFreeShipping);
        amaraProduct.setStatus(status);
        return amaraProduct;
    }
}
