package com.filber.refactor._3_field;

import java.util.HashSet;

/**
 * 将单向关联改为双向关联
 */
public class _24_ChangeUniToBid_222 {
    class OldCase {
        //Order-->Customer
        class Order {
            private Customer customer;

            public Customer getCustomer() {
                return customer;
            }

            public void setCustomer(Customer customer) {
                this.customer = customer;
            }
        }

        class Customer {
        }
    }

    //------------------------------------------------------------------
    class N2OneCase {
        //Order(n)-->Customer(1)
        class Order {
            private Customer customer;

            public Customer getCustomer() {
                return customer;
            }

            //Association维护函数放在Order侧
            public void setCustomer(Customer customer) {
                if (this.customer != null) {
                    this.customer.removeOrder(this);//断开旧的关系
                }
                this.customer = customer;
                if (this.customer != null) {
                    this.customer.friendOrders().add(this);//链接新的关系
                }
            }

            class Customer {
                private HashSet<Order> orders;

                //提供给Order使用的protected辅助函数!!
                protected HashSet<Order> friendOrders() {
                    return orders;
                }

                public void addOrder(Order order) {
                    order.setCustomer(this);//调用Order的Association维护函数
                }

                public void removeOrder(Order order) {
                    orders.remove(order);
                }
            }
        }
    }

    //------------------------------------------------------------------
    class N2NCase {
        class Order {
            private HashSet<Customer> customers;

            //Association维护函数放在Order侧
            public void addCustomer(Customer customer) {
                customers.add(customer);
                customer.friendOrders().add(this);
            }

            public void removeCustomer(Customer customer) {
                customers.remove(customer);
                customer.friendOrders().remove(this);
            }
        }

        class Customer {
            private HashSet<Order> orders;

            protected HashSet<Order> friendOrders() {
                return orders;
            }

            public void addOrder(Order order) {
                order.addCustomer(this);
            }

            public void removeOrder(Order order) {
                order.removeCustomer(this);
            }
        }
    }
}