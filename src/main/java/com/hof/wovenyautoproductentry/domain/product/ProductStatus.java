package com.hof.wovenyautoproductentry.domain.product;

public enum  ProductStatus {
    PASSIVE(0),
    ACTIVE(1);

    private int value;
    ProductStatus(int value) {

    }
    public int getValue() {
        return value;
    }
}
