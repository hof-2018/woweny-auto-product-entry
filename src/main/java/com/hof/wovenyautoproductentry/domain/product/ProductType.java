package com.hof.wovenyautoproductentry.domain.product;

public enum  ProductType {
    RUGS, PILLOWS;


    public static ProductType productTypeFactory(String weave) {

        if (weave.equalsIgnoreCase("RUGS"))
            return RUGS;

        if (weave.equalsIgnoreCase("PILLOWS"))
            return PILLOWS;

        return null;         // todo ?
    }
}
