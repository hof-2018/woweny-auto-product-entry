package com.hof.wovenyautoproductentry.domain.product;

import java.util.List;

public enum LeafCategory {

    ColorfulKilims("Colorful Kilims"),
    VintageNaturalModern("Vintage Natural | Modern"),
    Embroidered("Embroidered Kilims"),
    RagRugs("Rag Rugs"),
    HempKilims("Hemp Kilims"),

    KhotanCaucasian("Khotan | Caucasian"),
    TurkishAnatolian ("Turkish Anatolian"),
    PersianRugs("Persian Rugs"),
    TurkishOushak("Turkish Oushak"),

    SixteenXSixteen("16\"X16\"(40X40CM)"),
    SixteenXTwentyFour("16\"X24\"(40X60CM)"),
    TwentyXTwenty("20\"X20\"(50X50CM)"),
    FourTeenXTwenty("14\"X20\"(35X50CM)"),
    TwentyXTwentyEight("20\"X28\"(50X70CM)"),
    TwelveXTwentyFour("12\"X24\"(30X60CM)"),
    EighteenXEighteen("18\"X18\"(45X45CM)"),
    TwelveXTwenty("12\"X20\"(30X50CM)");

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
                .findAny().orElse(null);        // todo null?
    }
}
