package com.hof.wovenyautoproductentry.domain.product;

public enum Weave {
    Handwoven, Handknotted;
    //TODO uppercase

    public static Weave weaveFactory(String weave) {

        if (weave.equalsIgnoreCase("handwoven"))
            return Handwoven;

        if (weave.equalsIgnoreCase("handknotted"))
            return Handknotted;

        return null;          // TODO null -> exception
    }
}
