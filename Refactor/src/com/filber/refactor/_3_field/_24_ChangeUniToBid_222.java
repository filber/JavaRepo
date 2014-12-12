package com.filber.refactor._3_field;

import java.util.HashSet;

/**
 * Created by Administrator on 2014/11/30.
 */
public class _24_ChangeUniToBid_222 {
    static class OldOrder{
        private OldCustomer customer;
        public OldCustomer getCustomer() {
            return customer;
        }
        public void setCustomer(OldCustomer customer) {
            this.customer = customer;
        }
    }
    static class OldCustomer{
    }

    //Order与Customer的关系为 N对1
    static class NewOrder1 {
        private NewCustomer1 customer;
        public NewCustomer1 getCustomer() {
            return customer;
        }
        //Association维护函数放在Order侧
        public void setCustomer(NewCustomer1 customer) {
            if (this.customer!=null){
                this.customer.removeOrder(this);
            }
            this.customer = customer;
            if (this.customer!=null){
                this.customer.friendOrders().add(this);
            }
        }
    }
    static class NewCustomer1 {
        private HashSet<NewOrder1> orders;
        //提供给Order使用的辅助函数!!
        protected HashSet<NewOrder1> friendOrders(){
            return orders;
        }
        public void addOrder(NewOrder1 order){
            //调用Order的Association维护函数
            order.setCustomer(this);
        }
        public void removeOrder(NewOrder1 order){
            orders.remove(order);
        }
    }

    //Order与Customer的关系为 N对N
    static class NewOrder2{
        private HashSet<NewCustomer2> customers;
        //Association维护函数放在Order侧
        public void addCustomer(NewCustomer2 customer){
            customers.add(customer);
            customer.friendOrders().add(this);
        }
        public void removeCustomer(NewCustomer2 customer){
            customers.remove(customer);
            customer.friendOrders().remove(this);
        }
    }
    static class NewCustomer2{
        private HashSet<NewOrder2> orders;
        protected HashSet<NewOrder2> friendOrders(){
            return orders;
        }
        public void addOrder(NewOrder2 order){
            order.addCustomer(this);
        }
        public void removeOrder(NewOrder2 order){
            order.removeCustomer(this);
        }
    }
}
