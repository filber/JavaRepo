package com.filber.refactor._4_condition;

import java.util.Date;

/**
 * 分解条件表达式
 */
public class _34_DecomposeConditional_263 {
    public static final Date SUMMER_START = new Date();
    public static final Date SUMMER_END = new Date();

    private double winterRate;
    private double winterServiceCharge;
    private double summerRate;

    public double badCalCharge(Date date, int quantity) {
        double charge;
        if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
            charge = quantity * winterRate + winterServiceCharge;
        } else {
            charge = quantity * summerRate;
        }
        return charge;
    }

    //-------------------------------------------------------------------------------------------------
    //将if,then,else分别提取为独立的函数
    private boolean notSummer(Date date) {
        return date.before(SUMMER_START) || date.after(SUMMER_END);
    }

    private double winterCharge(int quantity) {
        return quantity * winterRate + winterServiceCharge;
    }

    private double summerCharge(int quantity) {
        return quantity * summerRate;
    }

    public double goodCalCharge(Date date, int quantity) {
        double charge;
        if (notSummer(date)) {
            charge = winterCharge(quantity);
        } else {
            charge = summerCharge(quantity);
        }
        return charge;
    }
}
