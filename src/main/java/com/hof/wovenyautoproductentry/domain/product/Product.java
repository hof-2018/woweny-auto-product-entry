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
    private String skuNumber;

    private String name;
    private String categoryPath;
    private String mainImageUrl;
    @ElementCollection(fetch = FetchType.EAGER)
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
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> metaKeyword = new HashSet<>();
    private String metaDescription;
    private Date wovenyCreateDate;
    private Date wovenyModifiedDate;
    private String age;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> colors = new HashSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> materials = new HashSet<>();
    private String region;
    private String size;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> styles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Weave weave;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String leafCategory;

    private boolean isUploadedChairish;
    private boolean isUploadedEtsy;

    public Product() {
    }

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

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String  getLeafCategory() {
        return leafCategory;
    }

    public void setLeafCategory(String leafCategory) {
        this.leafCategory = leafCategory;
    }

    public boolean isUploadedChairish() {
        return isUploadedChairish;
    }

    public void setUploadedChairish(boolean uploadedChairish) {
        isUploadedChairish = uploadedChairish;
    }

    public boolean isUploadedEtsy() {
        return isUploadedEtsy;
    }

    public void setUploadedEtsy(boolean uploadedEtsy) {
        isUploadedEtsy = uploadedEtsy;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", skuNumber='" + skuNumber + '\'' +
                ", name='" + name + '\'' +
                ", categoryPath='" + categoryPath + '\'' +
                ", mainImageUrl='" + mainImageUrl + '\'' +
                ", additionalImagePaths=" + additionalImagePaths +
                ", price=" + price +
                ", quantity=" + quantity +
                ", stockStatus='" + stockStatus + '\'' +
                ", status=" + status +
                ", lengthByCm=" + lengthByCm +
                ", widthByCm=" + widthByCm +
                ", lengthByInches='" + lengthByInches + '\'' +
                ", widthByInches='" + widthByInches + '\'' +
                ", metaKeyword=" + metaKeyword +
                ", metaDescription='" + metaDescription + '\'' +
                ", wovenyCreateDate=" + wovenyCreateDate +
                ", wovenyModifiedDate=" + wovenyModifiedDate +
                ", age='" + age + '\'' +
                ", colors=" + colors +
                ", materials=" + materials +
                ", region='" + region + '\'' +
                ", size='" + size + '\'' +
                ", styles=" + styles +
                ", weave=" + weave +
                ", productType=" + productType +
                ", leafCategory='" + leafCategory + '\'' +
                ", isUploadedChairish=" + isUploadedChairish +
                ", isUploadedEtsy=" + isUploadedEtsy +
                '}';
    }
}
