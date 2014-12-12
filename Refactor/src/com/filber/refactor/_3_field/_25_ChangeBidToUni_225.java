package com.filber.refactor._3_field;

import java.util.HashSet;

/**
 * Created by Administrator on 2014/12/1.
 */
public class _25_ChangeBidToUni_225 {

    //Order与Customer的关系为 N对1
    static class OldOrder {
        private OldCustomer customer;
        public OldCustomer getCustomer() {
            return customer;
        }
        //Association维护函数放在Order侧
        public void setCustomer(OldCustomer customer) {
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
    static class OldCustomer {
        private double discount;
        private HashSet<OldOrder> orders;
        //提供给Order使用的辅助函数!!
        protected HashSet<OldOrder> friendOrders(){
            return orders;
        }
        public void addOrder(OldOrder order){
            //调用Order的Association维护函数
            order.setCustomer(this);
        }
        public void removeOrder(OldOrder order){
            orders.remove(order);
        }
        public double getDiscount() {
            return discount;
        }
    }

    static class NewOrder {
        //Association维护函数放在Order侧
        public double getGrossPrice(){
            return  1.0;
        }
        public double getDiscountPrice(NewCustomer customer) {
            return getGrossPrice()*(1.0-customer.getDiscount());
        }
    }

    static class NewCustomer {
        private double discount;
        private HashSet<NewOrder> orders;
        //提供给Order使用的辅助函数!!
        protected HashSet<NewOrder> friendOrders(){
            return orders;
        }
        public void addOrder(NewOrder order){
            orders.add(order);
        }
        public void removeOrder(NewOrder order){
            orders.remove(order);
        }
        public double getDiscount() {
            return discount;
        }
    }
}
