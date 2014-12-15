package com.filber.refactor._3_field;

/**
 * 自封装字段
 */
public class _18_SelfEncapsulateField_196 {
    //类内部
    class OldCase{
        class IntRange {
            private int low, high;// 字段访问标识为private,类外部不可访问.

            boolean includes(int arg) {
                return arg >= low && arg <= high;//类内部跨过Getter直接访问字段.
            }

            void grow(int factor) {
                high = factor * high;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class NewCase{
        class IntRange {
            private int low, high;

            public IntRange(int low, int high) {
                //在构造函数中要谨慎使用Setter&Getter
                //因为Setter&Getter通常被认为对象创建之后才使用.
                this.low = low;
                this.high = high;
            }

            //除Constructor以外的方法尽量使用Setter&Getter操作字段.
            boolean includes(int arg) {
                return arg >= getLow() && arg <= getHigh();
            }

            void grow(int factor) {
                setHigh(factor * getHigh());
            }

            public int getLow() {
                return low;
            }

            public void setLow(int low) {
                this.low = low;
            }

            public int getHigh() {
                return high;
            }

            public void setHigh(int high) {
                this.high = high;
            }
        }

        class CappedRange extends IntRange {
            private final int cap;//CappedRange实例一旦创建则cap即不可更改.

            public CappedRange(int low, int high, int cap) {
                super(low, high);
                this.cap = cap;
            }
            //cap只暴露出来了Getter
            public int getCap() {
                return cap;
            }
            //Override了继承自父类的getHigh
            public int getHigh() {
                return Math.min(super.getHigh(), getCap());
            }
        }
    }
}