package com.filber.refactor._3_field;

/**
 * 以字段取代子类
 */
public class _33_ReplaceSubclassWithField_257 {
    class BadCase {
        abstract class Person {
            abstract boolean isMale();

            abstract String getCode();
        }

        //子类中只有单纯的Constant Method
        class Male extends Person {
            boolean isMale() {
                return true;
            }

            String getCode() {
                return "M";
            }
        }

        class Female extends Person {
            boolean isMale() {
                return false;
            }

            String getCode() {
                return "F";
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        class Person {
            private boolean isMale;
            private String code;

            public boolean isMale() {
                return isMale;
            }

            public void setMale(boolean isMale) {
                this.isMale = isMale;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}