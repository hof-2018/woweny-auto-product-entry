package com.hof.wovenyautoproductentry.domain.product;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class AmaraProduct {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String skuNumber;

    private String name;

    @Column(length = 500)
    private String metaDescription;

    @ElementCollection
    private List<String> styles = new ArrayList<>();

    private String weave;

    @ElementCollection
    private List<String> materials = new ArrayList<>();

    @ElementCollection
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

    @Enumerated(EnumType.STRING)
    private AmaraProductStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public String getWeave() {
        return weave;
    }

    public void setWeave(String weave) {
        this.weave = weave;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWidthByFInches() {
        return widthByFInches;
    }

    public void setWidthByFInches(String widthByFInches) {
        this.widthByFInches = widthByFInches;
    }

    public Integer getWidthByInches() {
        return widthByInches;
    }

    public void setWidthByInches(Integer widthByInches) {
        this.widthByInches = widthByInches;
    }

    public String getLengthByFInches() {
        return lengthByFInches;
    }

    public void setLengthByFInches(String lengthByFInches) {
        this.lengthByFInches = lengthByFInches;
    }

    public Integer getLengthByInches() {
        return lengthByInches;
    }

    public void setLengthByInches(Integer lengthByInches) {
        this.lengthByInches = lengthByInches;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPeriodMade() {
        return periodMade;
    }

    public void setPeriodMade(String periodMade) {
        this.periodMade = periodMade;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Boolean getHasImperfection() {
        return hasImperfection;
    }

    public void setHasImperfection(Boolean hasImperfection) {
        this.hasImperfection = hasImperfection;
    }

    public String getConditionDetails() {
        return conditionDetails;
    }

    public void setConditionDetails(String conditionDetails) {
        this.conditionDetails = conditionDetails;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Boolean getLocalPickup() {
        return isLocalPickup;
    }

    public void setLocalPickup(Boolean localPickup) {
        isLocalPickup = localPickup;
    }

    public Integer getLocalDeliveryMiles() {
        return localDeliveryMiles;
    }

    public void setLocalDeliveryMiles(Integer localDeliveryMiles) {
        this.localDeliveryMiles = localDeliveryMiles;
    }

    public Integer getPickupCharge() {
        return pickupCharge;
    }

    public void setPickupCharge(Integer pickupCharge) {
        this.pickupCharge = pickupCharge;
    }

    public Boolean getOwnShipping() {
        return isOwnShipping;
    }

    public void setOwnShipping(Boolean ownShipping) {
        isOwnShipping = ownShipping;
    }

    public Boolean getFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    public AmaraProductStatus getStatus() {
        return status;
    }

    public void setStatus(AmaraProductStatus status) {
        this.status = status;
    }
}
