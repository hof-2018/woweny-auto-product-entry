package com.hof.wovenyautoproductentry.domain.product;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private Long skuNumber;

    private String name;
    private String categoryPath;
    private String mainImageUrl;
    @ElementCollection
    private List<String> additionalImagePaths = new ArrayList<>();
    private Integer price;
    private Integer quantity;
    private String stockStatus;
    @Enumerated
    private ProductStatus status;
    private Integer lengthByCm;
    private Integer widthByCm;
    private String lengthByInches;
    private String widthByInches;
    @ElementCollection
    private Set<String> metaKeyword = new HashSet<>();
    @ElementCollection
    private Set<String> metaDescription = new HashSet<>();
    private Date wovenyCreateDate;
    private Date wovenyModifiedDate;
    private String age;
    @ElementCollection
    private Set<String> colors = new HashSet<>();
    @ElementCollection
    private Set<String> materials = new HashSet<>();
    private String region;
    @ElementCollection
    private Set<String> styles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Weave weave;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(Long skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public List<String> getAdditionalImagePaths() {
        return additionalImagePaths;
    }

    public void setAdditionalImagePaths(List<String> additionalImagePaths) {
        this.additionalImagePaths = additionalImagePaths;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Integer getLengthByCm() {
        return lengthByCm;
    }

    public void setLengthByCm(Integer lengthByCm) {
        this.lengthByCm = lengthByCm;
    }

    public Integer getWidthByCm() {
        return widthByCm;
    }

    public void setWidthByCm(Integer widthByCm) {
        this.widthByCm = widthByCm;
    }

    public String getLengthByInches() {
        return lengthByInches;
    }

    public void setLengthByInches(String lengthByInches) {
        this.lengthByInches = lengthByInches;
    }

    public String getWidthByInches() {
        return widthByInches;
    }

    public void setWidthByInches(String widthByInches) {
        this.widthByInches = widthByInches;
    }

    public Set<String> getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(Set<String> metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public Set<String> getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Set<String> metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Date getWovenyCreateDate() {
        return wovenyCreateDate;
    }

    public void setWovenyCreateDate(Date wovenyCreateDate) {
        this.wovenyCreateDate = wovenyCreateDate;
    }

    public Date getWovenyModifiedDate() {
        return wovenyModifiedDate;
    }

    public void setWovenyModifiedDate(Date wovenyModifiedDate) {
        this.wovenyModifiedDate = wovenyModifiedDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<String> getColors() {
        return colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public Set<String> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<String> materials) {
        this.materials = materials;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Set<String> getStyles() {
        return styles;
    }

    public void setStyles(Set<String> styles) {
        this.styles = styles;
    }

    public Weave getWeave() {
        return weave;
    }

    public void setWeave(Weave weave) {
        this.weave = weave;
    }
}
