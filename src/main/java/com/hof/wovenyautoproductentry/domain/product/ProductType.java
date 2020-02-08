package com.hof.wovenyautoproductentry.domain.product;

public enum ProductType {
    RUG, PILLOW;
    //TODO singular names!

    public static ProductType productTypeFactory(String weave) {

        if (weave.equalsIgnoreCase("RUGS"))
            return RUG;

        if (weave.equalsIgnoreCase("PILLOWS"))
            return PILLOW;

        return null;
        // throw new IllegalArgumentException("Product Type is not valid"); todo null
    }
}
