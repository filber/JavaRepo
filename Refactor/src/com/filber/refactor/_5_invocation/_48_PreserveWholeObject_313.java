package com.filber.refactor._5_invocation;

/**
 * 保留对象完整
 */
public class _48_PreserveWholeObject_313 {
    static class BadCase{
        static class TempRange{
            private int low;
            private int high;
            public int getLow() {
                return low;
            }
            public int getHigh() {
                return high;
            }
        }
        static class HeatingPlan{
            private TempRange range;
            public boolean withinRange(int low,int high){
                if (low>=range.getLow()&&high<=range.getHigh())return true;
                else return false;
            }
        }
        static class Room{
            private TempRange range;
            public boolean withinPlan(HeatingPlan plan){
                int low=range.getLow();
                int high=range.getHigh();
                return plan.withinRange(low,high);
            }
        }
    }

    static class GoodCase{
        static class TempRange{
            private int low;
            private int high;
            public int getLow() {
                return low;
            }
            public int getHigh() {
                return high;
            }
            public boolean includes(TempRange range){
                if (range.getLow()>= this.getLow()&&range.getHigh()<= this.getHigh())return true;
                else return false;
            }
        }
        static class HeatingPlan{
            private TempRange range;
            //通过使用已有的对象缩减参数长度
            public boolean withinRange(TempRange range){
                return this.range.includes(range);
            }
        }
        static class Room{
            private TempRange range;
            public boolean withinPlan(HeatingPlan plan){
                return plan.withinRange(range);//Delegate
            }
        }
    }
}
