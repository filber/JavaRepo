package com.filber.refactor._3_field;

/**
 * 封装字段
 */
public class _27_EncapsulateField_231 {
    class BadCase {
        //与SelfEncapsulateField不同,它的字段已经是private了.
        public String name;
    }
    //------------------------------------------------------------------
    class GoodCase {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
