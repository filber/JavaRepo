package com.filber.refactor._6_hierarchy;

/**
 * Created by Administrator on 2014/12/10.
 */
public class _60_PushDownMethod_353 {
    class OldCase{
        class Employee{
            int getQuota(){
                return 10;
            }
        }
        class Salesman extends Employee{}
    }

    class GoodCase{
        class Employee{
        }
        class Salesman extends Employee{
            //将逻辑上从属于子类的方法下移.
            int getQuota(){
                return 10;
            }
        }
    }
}
