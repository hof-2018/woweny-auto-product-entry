package com.hof.wovenyautoproductentry.domain.product;

import java.util.List;

public enum LeafCategory {

    ColorfulKilims("Colorful Kilims"),
    VintageNaturalModern("Vintage Modern Kilims"),
    Embroidered("Embroidered Kilims"),
    RagRugs("Rag Rugs"),
    HempKilims("Hemp Kilims"),

    KhotanCaucasian("Khotan | Caucasian Rugs"),
    TurkishAnatolian ("Turkish Anatolian Rugs"),
    PersianRugs("Persian Rugs"),
    TurkishOushak("Turkish Oushak Rugs"),

    SixteenXSixteen("16\"X16\"(40X40CM) Pillows"),
    SixteenXTwentyFour("16\"X24\"(40X60CM) Pillows"),
    TwentyXTwenty("20\"X20\"(50X50CM) Pillows"),
    FourTeenXTwenty("14\"X20\"(35X50CM) Pillows"),
    TwentyXTwentyEight("20\"X28\"(50X70CM) Pillows"),
    TwelveXTwentyFour("12\"X24\"(30X60CM) Pillows"),
    EighteenXEighteen("18\"X18\"(45X45CM) Pillows"),
    TwelveXTwenty("12\"X20\"(30X50CM) Pillows");

    private String value;

    LeafCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LeafCategory findLeafCategoryValue(String value){
        return List.of(LeafCategory.values()).stream()
                .filter(leafCategory -> leafCategory.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElse(null); // todo null
                //.orElseThrow(()-> new IllegalArgumentException("Leaf Category is not valid"));
    }
}
