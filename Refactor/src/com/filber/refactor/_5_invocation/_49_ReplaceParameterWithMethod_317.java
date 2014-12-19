package com.filber.refactor._5_invocation;

/**
 * 以函数取代参数
 */
public class _49_ReplaceParameterWithMethod_317 {
    class BadCase {
        private int quantity;
        private double itemPrice;

        public double getPrice() {
            double basePrice = quantity * itemPrice;
            int discountLevel;
            if (quantity > 100) discountLevel = 2;
            else discountLevel = 1;
            double finalPrice = discountedPrice(basePrice, discountLevel);
            return finalPrice;
        }

        private double discountedPrice(double basePrice, int discountLevel) {
            if (discountLevel == 2) return basePrice * 0.1;
            else return basePrice * 0.05;
        }
    }

    class GoodCase {
        public double getPrice() {
            if (discountLevel() == 2) return basePrice() * 0.1;
            else return basePrice() * 0.05;
        }

        private double basePrice() {
            return quantity * itemPrice;
        }

        private int discountLevel() {
            if (quantity > 100) return 2;
            else return 1;
        }

        private int quantity;
        private double itemPrice;
    }
}
