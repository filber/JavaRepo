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
            //daysOverdrawn仍是针对每个具体账户的属性,所以保留在Account.
            int _daysOverdrawn;

            double bankCharge() {
                double result = 4.5;
                result += _type.overdraftCharge(_daysOverdrawn);
                return result;
            }
        }
        class AccountType {
            boolean isPremium(){
                return false;
            }
            //搬移overdraftCharge至AccountType,并接收daysOverdrawn作为参数.
            double overdraftCharge(int daysOverdrawn) {
                if (daysOverdrawn<=0) {
                    return 0;
                }
                if (isPremium()) {
                    double result = 10;
                    if (daysOverdrawn>7) {
                        result += (daysOverdrawn-7)*0.85;
                    }
                    return result;
                } else {
                    return daysOverdrawn*1.75;
                }
            }
        }
    }
}
