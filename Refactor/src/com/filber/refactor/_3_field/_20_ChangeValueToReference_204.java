package com.filber.refactor._3_field;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 将值对象改为引用对象
 */
public class _20_ChangeValueToReference_204 {

    //-------------------------------------------------------------------------------------------------
    //Client Code.
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

    //Common Interface.
    interface Order {
        String getCustomer();

        void setCustomer(String customer);
    }

    //-------------------------------------------------------------------------------------------------
    static class ValueCustomer {
        final String name;

        public ValueCustomer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class ValueOrder implements Order {
        private ValueCustomer customer;

        public ValueOrder(String customerName) {
            this.customer = new ValueCustomer(customerName);
        }

        public String getCustomer() {
            return customer.getName();
        }

        public void setCustomer(String customer) {
            this.customer = new ValueCustomer(customer);//创建新的值对象
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class ReferenceCustomer {
        static final Dictionary<String, ReferenceCustomer> instances;

        static {
            instances = new Hashtable<String, ReferenceCustomer>();
            new ReferenceCustomer("George").store();
            new ReferenceCustomer("William").store();
            new ReferenceCustomer("Jack").store();
        }
        //提供工厂方法给外部获得引用对象.
        public static ReferenceCustomer getNamedCustomer(String name) {
            return instances.get(name);
        }

        private final String name;
        private String address;

        private ReferenceCustomer(String name) {
            this.name = name;
        }

        private void store() {
            instances.put(getName(), this);
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    static class ReferenceOrder implements Order {
        private ReferenceCustomer customer;

        public ReferenceOrder(String customerName) {
            this.customer = ReferenceCustomer.getNamedCustomer(customerName);//通过工厂方法取得引用对象
        }

        public String getCustomer() {
            return customer.getName();
        }

        public void setCustomer(String customerName) {
            this.customer = ReferenceCustomer.getNamedCustomer(customerName);//取得另外一个引用对象
        }
    }
}