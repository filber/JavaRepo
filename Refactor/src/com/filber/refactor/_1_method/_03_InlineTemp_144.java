package com.filber.refactor._1_method;

/**
 * 内联临时变量
 */
public class _03_InlineTemp_144 {
    private int basePrice(){
        return 1000;
    }
    public boolean badCase(){
        final int basePrice = basePrice();
        if (basePrice>1000)return true;
        else return false;
    }
    //-------------------------------------------------------------------------------------------------
    //临时变量只被赋值了一次的情况下,将其去除掉.
    public boolean goodCase(){
        return basePrice()>1000;
    }
}
