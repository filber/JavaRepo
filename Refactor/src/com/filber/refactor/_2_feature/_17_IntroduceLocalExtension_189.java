package com.filber.refactor._2_feature;

import java.util.Date;

/**
 * 引入本地扩展
 */
public class _17_IntroduceLocalExtension_189 {
    //当引入的外加函数(ForeignMethod)过多,散布在多个Client中时,就需要LocalExtension.

    //第一种实现:子类Extension
    //易于实现,需要在对象的创建时进行替换.
    //但容易产生新旧对象的多重引用,导致数据无法同步.
    class MfDateSub extends Date {
        private Date date;

        public MfDateSub(int year, int month, int day) {
            super(year, month, day);
        }

        public MfDateSub nextDate() {
            return new MfDateSub(this.getYear(), this.getMonth(), this.getDay() + 1);
        }
    }

    //-------------------------------------------------------------------------------------------------

    //第二种实现:包装类Extension
    //难于扩展,对象创建之后也可以进行替换.
    //可以使得新旧对象得以同步,但需要实现被包装类的其他方法.
    class MfDateWrap {
        private Date original;

        public MfDateWrap(Date original) {
            this.original = original;
        }

        public MfDateWrap nextDate() {
            return new MfDateWrap(new Date(original.getYear(), original.getMonth(), original.getDay() + 1));
        }

        // 要实现所有被包装类的方法
        public long getTime() {
            return original.getTime();
        }
        // xxxx

        //参数列表中有原类参数的方法要特别注意下面的两个场景!!

        //1.该方法只能做到向上兼容
        // 即MfDateWrap.after(Date)或MfDateWrap.after(MfDateWrap)是可以的.
        // 但Date.after(MfDateWrap)是不行的.
        // 对Client隐藏包装类存在.
        public boolean after(Date when) {
            return original.after(when);
        }

        // 2.完全对Client隐藏会带来如下问题,a.equals(b)失去交换律
        //MfDateWrap.equals(Date)为true
        //Date.equals(MfDateWrap)为false
        public boolean equals(Date obj) {
            return original.equals(obj);
        }
        //解决方案:用Overload的方式公开"我进行了包装"这一事实
        public boolean equalsDate(Date obj) {
            return original.equals(obj);
        }
        public boolean equalsDate(MfDateWrap obj) {
            return obj instanceof MfDateWrap && original.equals(obj.original);
        }
    }
}