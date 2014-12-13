package com.filber.refactor._2_feature;

/**
 * 移除中间人
 */
public class _15_RemoveMiddleMan_185 {
    class OldCase {
        class Person {
            String name;
            Department department;

            //Person中座了过多的简单委托动作.
            public Person getManager() {
                return department.getManager();
            }

            public double getAnnualBudget() {
                return department.getAnnualBudget();
            }

            public int getStaffCount() {
                return department.getStaffCount();
            }
        }

        class Department {
            Person manager;

            public Department(Person _manager) {
                this.manager = _manager;
            }

            public Person getManager() {
                return manager;
            }

            public double getAnnualBudget() {
                return 0;
            }

            public int getStaffCount() {
                return 0;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class NewCase {
        class Person {
            String name;
            Department department;

            //直接提供受托对象给Client调用.
            public Department getDepartment() {
                return department;
            }
        }

        class Department {
            Person manager;

            public Department(Person _manager) {
                this.manager = _manager;
            }

            public Person getManager() {
                return manager;
            }

            public double getAnnualBudget() {
                return 0;
            }

            public int getStaffCount() {
                return 0;
            }
        }
    }
}
