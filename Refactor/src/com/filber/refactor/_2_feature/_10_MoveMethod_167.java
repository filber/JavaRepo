package com.filber.refactor._2_feature;

/**
 * 搬移函数
 */
public class _10_MoveMethod_167 {

    class BadCase{
        class Account {
            AccountType _type;
            int _daysOverdrawn;

            //逻辑层面来说,overdraftCharge计算方式与具体的账户无关,与AccountType的关联更紧密.
            double overdraftCharge() {
                if (_type.isPremium()) {
                    double result = 10;
                    if (_daysOverdrawn>7) {
                        result += (_daysOverdrawn-7)*0.85;
                    }
                    return result;
                } else {
                    return _daysOverdrawn*1.75;
                }
            }
            double bankCharge(){
                double result = 4.5;
                if (_daysOverdrawn>0) {
                    result += overdraftCharge();
                }
                return result;
            }
        }
        class AccountType {
            boolean isPremium(){
                return false;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase{
        class Account {
            AccountType _type;
            int _daysOverdrawn;

            double bankCharge() {
                //消除了临时变量result
                return 4.5 + _type.overdraftCharge(_daysOverdrawn);
            }
        }
        class AccountType {
            boolean isPremium(){
                return false;
            }
            //搬移overdraftCharge至AccountType,并接收daysOverdrawn作为参数.
            double overdraftCharge(int daysOverdrawn) {
                if (daysOverdrawn<=0) return 0;//从Client中挪进来的逻辑.
                //将表达式的条件翻转,从而易于使用Guard Clauses
                if (!isPremium()) return daysOverdrawn*1.75;
                if (daysOverdrawn>7) return  10+(daysOverdrawn-7)*0.85;
                return 10;
            }
        }
    }
}
