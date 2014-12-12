package com.filber.refactor._4_condition;

/**
 * Created by Administrator on 2014/12/3.
 */
public class _36_ConsolidateDuplicateConditionalFragments_268 {

    private void send(){

    }

    public double badOperation(double price,boolean isSpecialDeal){
        double total;
        if (isSpecialDeal){
            total = 0.95*price;
            send();
        } else {
            total = 0.98*price;
            send();
        }
        return total;
    }
    //-------------------------------------------------------------------------------------------------
    // 通过将重复的代码片段搬移到外部
    // 凸显出哪些东西随着条件变化而变化,哪些东西保持不变
    public double goodOperation(double price,boolean isSpecialDeal){
        double total;
        if (isSpecialDeal){
            total = 0.95*price;
        } else {
            total = 0.98*price;
        }
        send();
        return total;
    }
}
