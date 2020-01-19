package com.hof.wovenyautoproductentry.domain.product;

import java.util.*;

public final class ProductBuilder {
    private Long id;
    private String skuNumber;
    private String name;
    private String categoryPath;
    private String mainImageUrl;
    private List<String> additionalImagePaths = new ArrayList<>();
    private Integer price;
    private Integer quantity;
    private String stockStatus;
    private ProductStatus status;
    private Integer lengthByCm;
    private Integer widthByCm;
    private String lengthByInches;
    private String widthByInches;
    private Set<String> metaKeyword = new HashSet<>();
    private String metaDescription;
    private Date wovenyCreateDate;
    private Date wovenyModifiedDate;
    private String age;
    private Set<String> colors = new HashSet<>();
    private Set<String> materials = new HashSet<>();
    private String region;
    private String size;
    private Set<String> styles = new HashSet<>();
    private Weave weave;
    private ProductType productType;
    private LeafCategory leafCategory;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder skuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder categoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
        return this;
    }

    public ProductBuilder mainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
        return this;
    }

    public ProductBuilder additionalImagePaths(List<String> additionalImagePaths) {
        this.additionalImagePaths = additionalImagePaths;
        return this;
    }

    public ProductBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    public ProductBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder stockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
        return this;
    }

    public ProductBuilder status(ProductStatus status) {
        this.status = status;
        return this;
    }

    public ProductBuilder lengthByCm(Integer lengthByCm) {
        this.lengthByCm = lengthByCm;
        return this;
    }

    public ProductBuilder widthByCm(Integer widthByCm) {
        this.widthByCm = widthByCm;
        return this;
    }

    public ProductBuilder lengthByInches(String lengthByInches) {
        this.lengthByInches = lengthByInches;
        return this;
    }

    public ProductBuilder widthByInches(String widthByInches) {
        this.widthByInches = widthByInches;
        return this;
    }

    public ProductBuilder metaKeyword(Set<String> metaKeyword) {
        this.metaKeyword = metaKeyword;
        return this;
    }

    public ProductBuilder metaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
        return this;
    }

    public ProductBuilder wovenyCreateDate(Date wovenyCreateDate) {
        this.wovenyCreateDate = wovenyCreateDate;
        return this;
    }

    public ProductBuilder wovenyModifiedDate(Date wovenyModifiedDate) {
        this.wovenyModifiedDate = wovenyModifiedDate;
        return this;
    }

    public ProductBuilder age(String age) {
        this.age = age;
        return this;
    }

    public ProductBuilder colors(Set<String> colors) {
        this.colors = colors;
        return this;
    }

    public ProductBuilder materials(Set<String> materials) {
        this.materials = materials;
        return this;
    }

    public ProductBuilder region(String region) {
        this.region = region;
        return this;
    }

    public ProductBuilder size(String size) {
        this.size = size;
        return this;
    }

    public ProductBuilder styles(Set<String> styles) {
        this.styles = styles;
        return this;
    }

    public ProductBuilder weave(Weave weave) {
        this.weave = weave;
        return this;
    }

    public ProductBuilder productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder leafCategory(LeafCategory leafCategory) {
        this.leafCategory = leafCategory;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setSkuNumber(skuNumber);
        product.setName(name);
        product.setCategoryPath(categoryPath);
        product.setMainImageUrl(mainImageUrl);
        product.setAdditionalImagePaths(additionalImagePaths);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setStockStatus(stockStatus);
        product.setStatus(status);
        product.setLengthByCm(lengthByCm);
        product.setWidthByCm(widthByCm);
        product.setLengthByInches(lengthByInches);
        product.setWidthByInches(widthByInches);
        product.setMetaKeyword(metaKeyword);
        product.setMetaDescription(metaDescription);
        product.setWovenyCreateDate(wovenyCreateDate);
        product.setWovenyModifiedDate(wovenyModifiedDate);
        product.setAge(age);
        product.setColors(colors);
        product.setMaterials(materials);
        product.setRegion(region);
        product.setSize(size);
        product.setStyles(styles);
        product.setWeave(weave);
        product.setProductType(productType);
        product.setLeafCategory(leafCategory);
        return product;
    }
}
