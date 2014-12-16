package com.filber.refactor._4_condition;

/**
 * 引入Null对象
 */
public class _40_IntroduceNullObject_285 {

    //------------------------------------------------------------------
    void oldCase() {
        Site site = new Site();
        Customer customer = site.getCustomer();
        BillingPlan plan;
        if (customer == null) plan = BillingPlan.basic();
        else plan = customer.getPlan();

        String customerName;
        if (customer == null) customerName = "occupant";
        else customerName = customer.getName();

        int weeksDelinquent;
        if (customer == null) weeksDelinquent = 0;
        else weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
    }

    //------------------------------------------------------------------
    //通过空对象去除掉了对于Null的判断.
    void newCase() {
        Site site = new Site();
        Customer customer = site.getCustomer();
        BillingPlan plan;
        plan = customer.getPlan();

        String customerName;
        customerName = customer.getName();

        int weeksDelinquent;
        weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();

        //两种方式判断customer是否为空对象
        //1.通过多态
        if (customer.isNull()) ;
        else ;

        //2.通过实例类型
        if (customer instanceof Null) ;
        else ;
    }

    //------------------------------------------------------------------
    class Site {
        private Customer customer;

        public Customer getCustomer() {
            if (customer == null) return Customer.createNull();
            else return customer;
        }
    }

    static class Customer {
        private PaymentHistory history;
        private BillingPlan plan;

        public String getName() {
            return "";
        }

        public BillingPlan getPlan() {
            return plan;
        }

        public PaymentHistory getHistory() {
            return history;
        }

        //在父类中增加isNull方法.
        public boolean isNull() {
            return false;
        }

        public static Customer createNull() {
            return new NullCustomer();
        }
    }

    static class PaymentHistory {
        public static PaymentHistory createNull() {
            return new NullPaymentHistory();
        }

        public int getWeeksDelinquentInLastYear() {
            return 10;
        }
    }

    static class BillingPlan {
        public static BillingPlan basic() {
            return new BillingPlan();
        }
    }

    //-------------------------------------------------------------------------------------------------
    //第一个空对象
    static class NullPaymentHistory extends PaymentHistory {
        public int getWeeksDelinquentInLastYear() {
            return 0;
        }
    }

    interface Null {
    }

    //第二个空对象
    static class NullCustomer extends Customer implements Null {
        public String getName() {
            return "occupant";
        }

        public boolean isNull() {
            return true;
        }

        public PaymentHistory getHistory() {
            return PaymentHistory.createNull();
        }

        public BillingPlan getPlan() {
            return BillingPlan.basic();
        }
    }
}
