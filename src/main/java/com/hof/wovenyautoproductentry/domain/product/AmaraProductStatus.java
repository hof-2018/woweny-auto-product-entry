package com.hof.wovenyautoproductentry.domain.product;

public enum AmaraProductStatus {
    ALREADY_PUBLISHED(1), READY_TO_PUBLISH(0), NOT_ELIGIBLE_TO_PUBLISH(-1);

    private final int id;

    AmaraProductStatus(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }

    public static AmaraProductStatus productStatus(String status) {

        if (status.equalsIgnoreCase("-1"))
            return NOT_ELIGIBLE_TO_PUBLISH;

        if (status.equalsIgnoreCase("0"))
            return READY_TO_PUBLISH;

        if (status.equalsIgnoreCase("1"))
            return ALREADY_PUBLISHED;

        throw new IllegalArgumentException("Unknown status");
    }
}
