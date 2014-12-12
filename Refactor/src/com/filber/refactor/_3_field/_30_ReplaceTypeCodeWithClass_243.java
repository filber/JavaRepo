package com.filber.refactor._3_field;

/**
 * Created by Administrator on 2014/12/2.
 */
public class _30_ReplaceTypeCodeWithClass_243 {
    static class OldPerson{
        public static final int O = 0;
        public static final int A = 1;
        public static final int B = 2;
        public static final int AB = 3;

        private int _bloodGroup;
        OldPerson(int bloodGroup) {
            this._bloodGroup = bloodGroup;
        }
        public int getBloodGroup() {
            return _bloodGroup;
        }
        public void setBloodGroup(int bloodGroup) {
            this._bloodGroup = bloodGroup;
        }
    }

    //类型码不会影响宿主类的行为,则使用Replace Type Code with Class来处理它们
    static class BloodGroup{
        public static final BloodGroup O = new BloodGroup(0);
        public static final BloodGroup A = new BloodGroup(1);
        public static final BloodGroup B = new BloodGroup(2);
        public static final BloodGroup AB = new BloodGroup(3);
        private static final BloodGroup[] _values = {O,A,B,AB};

        //三个过渡函数
//        public static BloodGroup code(int code){
//            return _values[code];
//        }
//        public int getCode() {
//            return _code;
//        }
//        public void setCode(int code) {
//            this._code = code;
//        }
        private int _code;
        private BloodGroup(int code) {
            this._code = code;
        }
    }
    static class NewPerson{
        private BloodGroup _bloodGroup;
        NewPerson(BloodGroup _bloodGroup) {
            this._bloodGroup = _bloodGroup;
        }
        public BloodGroup getBloodGroup() {
            return _bloodGroup;
        }
        public void setBloodGroup(BloodGroup bloodGroup) {
            this._bloodGroup = bloodGroup;
        }
    }
}
