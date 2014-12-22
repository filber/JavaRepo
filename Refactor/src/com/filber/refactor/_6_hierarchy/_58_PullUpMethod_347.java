package com.filber.refactor._6_hierarchy;

import java.util.Date;

/**
 * 函数上移
 */
public class _58_PullUpMethod_347 {
    class BadCase {
        class Customer {
            protected Date lastBillDate;

            protected void addBill(Date date, Double amount) {
            }
        }

        class RegularCustomer extends Customer {
            public void createBill(Date date) {
                double chargeAmount = chargeFor(lastBillDate, date);
                addBill(date, chargeAmount);
            }

            private double chargeFor(Date start, Date end) {
                return 2.0;
            }
        }

        class PreferredCustomer extends Customer {
            public void createBill(Date date) {
                double chargeAmount = chargeFor(lastBillDate, date);
                addBill(date, chargeAmount);
            }

            private double chargeFor(Date start, Date end) {
                return 1.0;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        abstract class Customer {
            protected Date lastBillDate;

            protected void addBill(Date date, Double amount) {
            }

            public void createBill(Date date) {
                double chargeAmount = chargeFor(lastBillDate, date);
                addBill(date, chargeAmount);
            }

            //将上拉函数中使用到的子类方法声明为protected abstract,在子类中予以特征化实现.
            protected abstract double chargeFor(Date start, Date end);
        }

        class RegularCustomer extends Customer {
            protected double chargeFor(Date start, Date end) {
                return 2.0;
            }
        }

        class PreferredCustomer extends Customer {
            protected double chargeFor(Date start, Date end) {
                return 1.0;
            }
        }
    }
}
