package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/8.
 */
public class _51_RemoveSettingMethod_325 {
    static class BadCase{
        static class Account{
            private String id;
            Account(String id) {
                this.id = id;
            }
            public String getId() {
                return id;
            }
            public void setId(String id) {
                this.id = id;
            }
        }
    }

    static class GoodCase{
        static class Account{
            final private String id;
            Account(String id) {
                this.id = id;
            }
            public String getId() {
                return id;
            }
        }
        static class InterestAccount extends Account{
            final private double interest;
            InterestAccount(double interest,String id) {
                super(id);
                this.interest = interest;
            }
            public double getInterest() {
                return interest;
            }
        }
    }
}
