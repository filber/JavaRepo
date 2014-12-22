package com.filber.refactor._7_huge;

import java.util.Vector;

/**
 * 将过程化设计转化为对象设计
 */
public class _70_ConvertProceduralDesignToObjects_393 {
    class BadCase {
        class Order {
            private Vector<OrderLine> orderLines;
        }

        class OrderLine {
            private int quantity;
            private double price;
        }

        class OrderCalculator {
            double determinePrice(Order order) {
                double totalPrice = 0.0;
                for (OrderLine orderLine : order.orderLines) {
                    totalPrice += (orderLine.quantity * orderLine.price);
                }
                return totalPrice;
            }

            double determineTaxes(Order order) {
                double totalTaxes = 0.0;
                for (OrderLine orderLine : order.orderLines) {
                    double orderPrice = orderLine.quantity * orderLine.price;
                    if (orderPrice <= 100.0) {
                        totalTaxes += orderPrice * 0.05;
                    } else {
                        totalTaxes += 5 + (orderPrice - 100) * 0.08;
                    }
                }
                return totalTaxes;
            }
        }
    }

    //------------------------------------------------------------------
    //将纯稚的数据记录变为对象，将大块的行为分成小块，并将行为移至相关对象中。
    class GoodCase {
        class OrderCalculator {
            double determinePrice(Order order) {
                return order.getPrice();
            }

            double determineTaxes(Order order) {
                return order.getTaxes();
            }
        }

        class Order {
            private Vector<OrderLine> orderLines;

            double getPrice() {
                double totalPrice = 0.0;
                for (OrderLine orderLine : orderLines) {
                    totalPrice += orderLine.getPrice();
                }
                return totalPrice;
            }

            double getTaxes() {
                double totalTaxes = 0.0;
                for (OrderLine orderLine : orderLines) {
                    totalTaxes += orderLine.getTaxes();
                }
                return totalTaxes;
            }
        }

        class OrderLine {
            private String productName;
            private int quantity;
            private double price;

            double getPrice() {
                return quantity * price;
            }

            double getTaxes() {
                if (getPrice() <= 100.0) return getPrice() * 0.05;
                else return 5 + (getPrice() - 100) * 0.08;
            }
        }
    }
}
