package com.filber.refactor._1_method;

/**
 * 内联函数.
 */
public class _02_InlineMethod_142 {
    //非必要的间接性总是让人不舒服
    class BadCase{
        private int numberOfLateDeliveries;
        public int getRating(){
            return moreThanFiveLateDeliveries()?2:1;
        }
        private boolean moreThanFiveLateDeliveries(){
            return numberOfLateDeliveries>5;
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase{
        private int numberOfLateDeliveries;
        public int getRating(){
            return numberOfLateDeliveries>5?2:1;
        }
    }
}
