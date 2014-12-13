package com.filber.refactor._2_feature;

import java.util.Date;

/**
 * 引入外加函数
 */
public class _16_IntroduceForeignMethod_187 {
    public static final Date previousEnd = new Date();
    public static final Date newStart = nextDay(previousEnd);

    //通过在Client中添加外加函数,扩充无法修改类的功能.
    public static Date nextDay(Date arg) {
        //Foreign Method, should be on Date.
        return new Date(arg.getYear(), arg.getMonth(), arg.getDay() + 1);
    }
}
