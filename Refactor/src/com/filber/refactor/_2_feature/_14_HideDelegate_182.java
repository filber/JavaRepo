package com.filber.refactor._2_feature;

/**
 * 隐藏委托关系
 */
public class _14_HideDelegate_182 {

    class OldCase{
        void clientCode(){
            new Person().getDepartment().getManager();//需要使用委托才可以得到Manager.
        }
        class Person{
            String name;
            Department department;
            public Department getDepartment() {
                return department;
            }
        }
        class Department{
            Person manager;
            public Person getManager() {
                return manager;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class NewCase{
        void clientCode(){
            new Person().getManager();//HideDelegate后可以直接得到Manager.
        }
        class Person{
            String name;
            Department department;
            public Department getDepartment() {
                return department;
            }
            public Person getManager(){
                return department.getManager();
            }
        }
        class Department{
            Person manager;
            public Person getManager() {
                return manager;
            }
        }
    }
}