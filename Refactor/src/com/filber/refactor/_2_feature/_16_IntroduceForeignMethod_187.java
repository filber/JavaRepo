package com.filber.refactor._2_feature;

import java.util.Date;

public class _16_IntroduceForeignMethod_187 {
    public static final Date previousEnd = new Date();
    public static final Date newStart = nextDay(previousEnd);

    private static Date nextDay(Date arg){
        //Foreign Method, should be on Date.
        return new Date(arg.getYear(),arg.getMonth(),arg.getDay()+1);
    }
}
