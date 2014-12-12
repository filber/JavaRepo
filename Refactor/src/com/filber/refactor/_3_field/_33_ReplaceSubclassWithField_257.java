package com.filber.refactor._3_field;

/**
 * Created by Administrator on 2014/12/2.
 */
public class _33_ReplaceSubclassWithField_257 {

    static abstract class OldPerson{
        abstract boolean isMale();
        abstract String getCode();
    }


    //子类中只有单纯的Constant Method
    static class Male extends OldPerson{
        @Override
        boolean isMale() {
            return true;
        }
        @Override
        String getCode() {
            return "M";
        }
    }
    static class Female extends OldPerson{
        @Override
        boolean isMale() {
            return false;
        }
        @Override
        String getCode() {
            return "F";
        }
    }

    static class NewPerson{
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
