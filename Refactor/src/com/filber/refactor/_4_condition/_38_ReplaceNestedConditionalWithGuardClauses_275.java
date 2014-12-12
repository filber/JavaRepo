package com.filber.refactor._4_condition;

/**
 * Created by Administrator on 2014/12/3.
 */
public class _38_ReplaceNestedConditionalWithGuardClauses_275 {
    private boolean isDead;
    private boolean isSeparated;
    private boolean isRetired;

    private double deadAmount(){return 0.0;}
    private double separatedAmount(){return 0.0;}
    private double retiredAmount(){return 0.0;}
    private double normalAmount(){return 0.0;}

    public double badGetPayAmount(){
        double result;
        if (isDead) {
            result = deadAmount();
        } else {
            if (isSeparated){
                result = separatedAmount();
            } else {
                if (isRetired){
                    result = retiredAmount();
                } else {
                    result = normalAmount();
                }
            }
        }
        return result;
    }
    //-------------------------------------------------------------------------------------------------
    // 使用卫语句(Guard Clauses)来处理异常情况,去除嵌套循环的对代码可读性的影响.
    public double goodGetPayAmount(){
        if (isDead) return deadAmount();
        if (isSeparated) return separatedAmount();
        if (isRetired) return retiredAmount();
        return normalAmount();
    }
    //-------------------------------------------------------------------------------------------------

    private double capital;
    private double intRate;
    private double duration;
    private double income;
    private static double ADJ_FACTOR;

    public double badGetAdjustedCapital(){
        double result = 0.0;
        if (capital>=0.0){
            if (intRate>0.0&&duration>0.0){
                result = (income/duration)*ADJ_FACTOR;
            }
        }
        return result;
    }
    //-------------------------------------------------------------------------------------------------
    //将表达式的条件翻转,从而易于使用Guard Clauses
    public double goodGetAdjustedCapital(){
        if (capital<0.0) return 0.0;
        if (intRate<=0.0)return 0.0;
        if (duration<=0.0)return 0.0;
        return (income/duration)*ADJ_FACTOR;
    }
}
