package com.filber.refactor._7_huge;

import java.util.Vector;

public class _69_ConvertProceduralDesignToObjects_393 {
    class BadCase{
        class Order{}
        class OrderLine{}
        class OrderCalculator{
            double determinePrice(Order order){
                return 0;
            }
            double determineTaxes(Order order){
                return 0;
            }
        }
    }

    //将纯稚的数据记录变为对象，将大块的行为分成小块，并将行为移至相关对象中。
    class GoodCase{
        class Order{
            private Vector<OrderLine> orderLines;
            double getPrice(){
                double totalPrice=0.0;
                for(OrderLine orderLine:orderLines){
                    totalPrice+=orderLine.getPrice();
                }
                return totalPrice;
            }
            double getTaxes(){
                double totalTaxes=0.0;
                for(OrderLine orderLine:orderLines){
                    totalTaxes+=orderLine.getTaxes();
                }
                return totalTaxes;
            }
        }
        class OrderLine{
            private String productName;
            private int quantity;
            private double price;
            double getPrice(){
                return quantity*price;
            }
            double getTaxes(){
                if (getPrice()<=100.0) return getPrice()*0.05;
                else return 5+(getPrice()-100)*0.08;
            }
        }
    }
}
