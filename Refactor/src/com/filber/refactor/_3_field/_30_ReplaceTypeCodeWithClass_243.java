package com.filber.refactor._3_field;

/**
 * 以类取代类型码
 */
public class _30_ReplaceTypeCodeWithClass_243 {
    class BadCase {
        class Person {
            public static final int O = 0;
            public static final int A = 1;
            public static final int B = 2;
            public static final int AB = 3;

            private int bloodGroup;

            Person(int bloodGroup) {
                this.bloodGroup = bloodGroup;
            }

            public int getBloodGroup() {
                return bloodGroup;
            }

            public void setBloodGroup(int bloodGroup) {
                this.bloodGroup = bloodGroup;
            }
        }
    }

    //------------------------------------------------------------------
    //类型码不会影响宿主类的行为时,使用Replace Type Code with Class来处理它们
    static class GoodCase {
        static class BloodGroup {
            public static final BloodGroup O = new BloodGroup(0);
            public static final BloodGroup A = new BloodGroup(1);
            public static final BloodGroup B = new BloodGroup(2);
            public static final BloodGroup AB = new BloodGroup(3);
            private static final BloodGroup[] BLOOD_VALUES = {O, A, B, AB};

            private int code;

            //Constructor声明为private
            private BloodGroup(int code) {
                this.code = code;
            }
        }

        class Person {
            private BloodGroup bloodGroup;

            Person(BloodGroup bloodGroup) {
                this.bloodGroup = bloodGroup;
            }

            public BloodGroup getBloodGroup() {
                return bloodGroup;
            }

            public void setBloodGroup(BloodGroup bloodGroup) {
                this.bloodGroup = bloodGroup;
            }
        }
    }

    //------------------------------------------------------------------
    //相比Class,使用枚举更加方便.
    static class EnumCase {
        enum BloodGroup {
            O, A, B, AB
        }

        class Person {
            private BloodGroup bloodGroup;

            Person(BloodGroup bloodGroup) {
                this.bloodGroup = bloodGroup;
            }

            public BloodGroup getBloodGroup() {
                return bloodGroup;
            }

            public void setBloodGroup(BloodGroup bloodGroup) {
                this.bloodGroup = bloodGroup;
            }
        }
    }
}
