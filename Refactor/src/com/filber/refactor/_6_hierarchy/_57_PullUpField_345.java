package com.filber.refactor._6_hierarchy;

/**
 * Created by Administrator on 2014/12/10.
 */
public class _57_PullUpField_345 {
    class OldCase{
        class Employee{}
        class Salesman{
            private String name;
        }
        class Engineer{
            private String name;
        }
    }

    //将子类中private的属性在父类中声明为protected.
    class NewCase{
        class Employee{
            protected String name;
        }
        class Salesman{}
        class Engineer{}
    }
}
