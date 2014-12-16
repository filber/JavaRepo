package com.filber.refactor._3_field;

/**
 * 以子类取代类型码
 */
public class _31_ReplaceTypeCodeWithSubclasses_248 {

    // 1.类型码会影响宿主类的行为
    // 2.类型码运行期不可变

    static class OldCase {
        static class Employee {
            private int type;
            static final int ENGINEER = 0;
            static final int SALESMAN = 1;
            static final int MANAGER = 2;

            Employee(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public int getSalary() {
                if (type == ENGINEER) {
                    return 100;
                } else if (type == SALESMAN) {
                    return 200;
                } else if (type == MANAGER) {
                    return 300;
                } else {
                    return 0;
                }
            }
        }
    }

    //------------------------------------------------------------------
    static class NewCase {
        // 去除了type字段
        // 将根据type判断采取不同动作的行为getSalary放在了子类中实现.
        static abstract class Employee {
            static final int ENGINEER = 0;
            static final int SALESMAN = 1;
            static final int MANAGER = 2;

            //FactoryMethod根据不同的类型码返回对应的子类.
            public static Employee createEmployee(int type) {
                if (type == ENGINEER) {
                    return new Engineer();
                } else if (type == SALESMAN) {
                    return new Salesman();
                } else if (type == MANAGER) {
                    return new Manager();
                } else {
                    throw new IllegalArgumentException();
                }
            }

            //仅返回常量值的:常量函数(Constant Method)
            public abstract int getSalary();
        }

        static class Engineer extends Employee {
            public int getSalary() {
                return 100;
            }
        }

        static class Salesman extends Employee {
            public int getSalary() {
                return 200;
            }
        }

        static class Manager extends Employee {
            public int getSalary() {
                return 300;
            }
        }
    }
}