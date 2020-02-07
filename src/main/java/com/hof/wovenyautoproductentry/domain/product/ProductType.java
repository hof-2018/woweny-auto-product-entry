package com.hof.wovenyautoproductentry.domain.product;

public enum  ProductType {
    RUGS, PILLOWS;
    //TODO singular names!

    public static ProductType productTypeFactory(String weave) {

        if (weave.equalsIgnoreCase("RUGS"))
            return RUGS;

        if (weave.equalsIgnoreCase("PILLOWS"))
            return PILLOWS;

        return null;         // TODO exception
    }
}
