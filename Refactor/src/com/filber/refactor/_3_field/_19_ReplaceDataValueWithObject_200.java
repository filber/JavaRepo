package com.filber.refactor._3_field;

import java.util.Collection;
import java.util.Iterator;

/**
 * 以对象取代数值
 */
public class _19_ReplaceDataValueWithObject_200 {

    static int numberOfOrdersFor(Collection<Order> orders, String customer) {
        int result = 0;
        Iterator<Order> orderIterator = orders.iterator();
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();
            if (order.getCustomer().equals(customer)) {
                result++;
            }
        }
        return result;
    }

    interface Order {
        String getCustomer();
        void setCustomer(String customer);
    }

    //-------------------------------------------------------------------------------------------------
    class OldOrder implements Order {
        String customer;

        public OldOrder(String customer) {
            this.customer = customer;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }
    }

    //-------------------------------------------------------------------------------------------------
    //抽取出值对象Customer
    class Customer {
        //可以追加Method,但不适于追加信用等级,地址之类的可变属性.
        final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        //值对象一般需要重写hashCode和equals
        public int hashCode() {
            return name.hashCode();
        }

        //值对象的同一性体现于内部值是否相等,而非是否是一个对象.
        public boolean equals(Object obj) {
            return obj instanceof Customer && getName().equals(((Customer) obj).getName());
        }
    }

    class GoodOrder implements Order {
        private Customer customer;

        public String getCustomer() {
            return customer.getName();
        }

        public void setCustomer(String customer) {
            this.customer = new Customer(customer);
        }
    }
}