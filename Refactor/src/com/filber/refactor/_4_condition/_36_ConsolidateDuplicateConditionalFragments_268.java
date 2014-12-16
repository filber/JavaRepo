package com.filber.refactor._4_condition;

/**
 * 合并重复的条件片段
 */
public class _36_ConsolidateDuplicateConditionalFragments_268 {

    class BadCase {
        public double badFragments(double price, boolean isSpecialDeal) {
            double total;
            if (isSpecialDeal) {
                total = 0.95 * price;
                if (total > 1000) System.out.println("Total Exceed Limit.");
            } else {
                total = 0.98 * price;
                if (total > 1000) System.out.println("Total Exceed Limit.");
            }
            return total;
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        // 通过将重复的代码片段搬移到外部
        // 凸显出哪些东西随着条件变化而变化,哪些东西保持不变
        public double goodOperation(double price, boolean isSpecialDeal) {
            double total;
            if (isSpecialDeal) {
                total = 0.95 * price;
            } else {
                total = 0.98 * price;
            }
            alert(total);
            return total;
        }

        private void alert(double total) {
            if (total > 1000) System.out.println("Total Exceed Limit.");
        }
    }

}
