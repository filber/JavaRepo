package com.filber.refactor._3_field;

import java.util.HashSet;

/**
 * 将双向关联改为单向关联
 */
public class _25_ChangeBidToUni_225 {
    //频繁的双向关联容易产生僵尸对象
    class BidCase{
        //Order----(Aggregation)---->Customer
        class Order {
            private Customer customer;
            public Customer getCustomer() {
                return customer;
            }
            public void setCustomer(Customer customer) {
                if (this.customer!=null){
                    this.customer.removeOrder(this);
                }
                this.customer = customer;
                if (this.customer!=null){
                    this.customer.friendOrders().add(this);
                }
            }
            public double getGrossPrice(){
                return  1.0;
            }
            public double getDiscountPrice() {
                return getGrossPrice()*(1.0-customer.getDiscount());
            }
        }
        class Customer {
            private double discount;
            private HashSet<Order> orders;
            protected HashSet<Order> friendOrders(){
                return orders;
            }
            public void addOrder(Order order){
                order.setCustomer(this);
            }
            public void removeOrder(Order order){
                orders.remove(order);
            }
            public double getDiscount() {
                return discount;
            }
        }
    }

    class UniCase{
        //Order----(Dependency)---->Customer
        class Order {
            public double getGrossPrice(){
                return  1.0;
            }
            //通过参数使得aggregation-->dependency
            public double getDiscountPrice(Customer customer) {
                return getGrossPrice()*(1.0-customer.getDiscount());
            }
        }

        class Customer {
            private double discount;
            private HashSet<Order> orders;
            public void addOrder(Order order){
                orders.add(order);
            }
            public void removeOrder(Order order){
                orders.remove(order);
            }
            public double getDiscount() {
                return discount;
            }
        }
    }
}
