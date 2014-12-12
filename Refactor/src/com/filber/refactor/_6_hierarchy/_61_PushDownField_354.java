package com.filber.refactor._6_hierarchy;

public class _61_PushDownField_354 {
    class OldCase{
        class Employee{
            protected int quota;
        }
        class Salesman extends Employee{}
    }

    class GoodCase{
        class Employee{
        }
        class Salesman extends Employee{
            //将不能被全部子类使用到的属性下移.
            protected int quota;
        }
    }
}
