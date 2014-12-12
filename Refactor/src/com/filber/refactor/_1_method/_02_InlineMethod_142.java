package com.filber.refactor._1_method;

public class _02_InlineMethod_142 {
    //非必要的间接性总是让人不舒服
    class OldCase{
        private int numberOfLateDeliveries;
        public int getRating(){
            return moreThanFiveLateDeliveries()?2:1;
        }
        private boolean moreThanFiveLateDeliveries(){
            return numberOfLateDeliveries>5;
        }
    }
}
