package com.filber.refactor._2_feature;

/**
 * 搬移函数
 */
public class _10_MoveMethod_167 {

    class BadCase {
        class Account {
            AccountType type;
            int daysOverdrawn;

            //逻辑层面来说,overdraftCharge计算方式与具体的账户无关,与AccountType的关联更紧密.
            public double overdraftCharge() {
                if (type.isPremium()) {
                    double result = 10;
                    if (daysOverdrawn > 7) {
                        result += (daysOverdrawn - 7) * 0.85;
                    }
                    return result;
                } else {
                    return daysOverdrawn * 1.75;
                }
            }

            public double bankCharge() {
                double result = 4.5;
                if (daysOverdrawn > 0) {
                    result += overdraftCharge();
                }
                return result;
            }
        }

        class AccountType {
            public boolean isPremium() {
                return false;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        class Account {
            AccountType type;
            int daysOverdrawn;

            public double bankCharge() {
                return 4.5 + type.overdraftCharge(daysOverdrawn);//消除了临时变量result
            }
        }

        class AccountType {
            //降低isPremium的访问级别为private,因为重构之后它已不被Account访问.
            private boolean isPremium() {
                return false;
            }

            //搬移overdraftCharge至AccountType,并接收daysOverdrawn作为参数.
            public double overdraftCharge(int daysOverdrawn) {
                if (daysOverdrawn <= 0) return 0;//从Client中挪进来的逻辑.
                if (!isPremium()) return daysOverdrawn * 1.75;//将表达式的条件翻转,消除嵌套IF.
                if (daysOverdrawn > 7) return 10 + (daysOverdrawn - 7) * 0.85;
                return 10;
            }
        }
    }
}
