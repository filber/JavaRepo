package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/5.
 */
public class _46_ParameterizeMethod_308 {
    static class Dollars{
        private double amt;
        Dollars(double amt) {
            this.amt = amt;
        }
    }

    private int lastUsage(){
        return 100;
    }

    public Dollars badBaseCharge(){
        double result = Math.min(lastUsage(),100)*0.03;
        if (lastUsage()>100){
            result += (Math.min(lastUsage(),200)-100)*0.05;
        }
        if (lastUsage()>200){
            result += (lastUsage()-200)*0.07;
        }
        return new Dollars(result);
    }

    //-------------------------------------------------------------------------------------------------
    // 1.使用ExtractMethod提取出重复的逻辑
    // 2.使用ParameterizeMethod为提取出的函数添加参数
    private double usageInDollars(int from,int to,double factor){
        if (lastUsage()>from) return (Math.min(lastUsage(),to)-from)*factor;
        else return 0;
    }

    public Dollars goodBaseCharge(){
        double result = usageInDollars(0,100,0.03);
        result += usageInDollars(100,200,0.05);
        result += usageInDollars(200,Integer.MAX_VALUE,0.07);
        return new Dollars(result);
    }
}
