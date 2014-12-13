package com.filber.refactor._2_feature;

/**
 * 搬移字段
 */
public class _11_MoveField_171 {
    class BadCase{
        class Account {
            AccountType type;
            //逻辑上讲利率(interestRate)与计算方式同账户类型(AccountType)的关联更紧密.
            double interestRate;
            double interestForAmountDays(double amount,int days) {
                return interestRate *amount*days/365;
            }
        }
        class AccountType {
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase{
        class Account {
            AccountType type;
            //如果Account.interestForAmountDays已经暴露给过多Client,无法删除,则使用HideDelegate.
            public double interestForAmountDays(double amount,int days) {
                return type.interestForAmountDays(amount,days);
            }
        }
        class AccountType {
            //将属性和方法一起搬移至AccountType中.
            double interestRate;
            double interestForAmountDays(double amount,int days) {
                return interestRate *amount*days/365;
            }
        }
    }
}
