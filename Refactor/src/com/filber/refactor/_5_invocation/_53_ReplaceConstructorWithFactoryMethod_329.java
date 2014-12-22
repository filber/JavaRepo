package com.filber.refactor._5_invocation;

/**
 * 以工厂函数取代构造函数
 */
public class _53_ReplaceConstructorWithFactoryMethod_329 {
    //构造函数只能返回单一类型的对象
    class ConstructorCase {
        class Employee {
            private int type;

            public Employee(int type) {
                this.type = type;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    //通过工厂函数,将"对象创建请求的接受者"和"被创建对象所属的类"分离.
    static class FactoryMethodCase1 {
        static class Employee {
            public static Employee create(int type) {
                return new Employee(type);
            }

            private int type;

            private Employee(int type) {
                this.type = type;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class FactoryMethodCase2 {
        static class Employee {
            public static final int ENGINEER = 1;
            public static final int SALESMAN = 2;

            //将子类对用户隐藏起来.
            public static Employee create(int type) {
                if (ENGINEER == type) {
                    return new Engineer();
                } else if (SALESMAN == type) {
                    return new Salesman();
                } else {
                    throw new IllegalArgumentException("Incorrect type code value");
                }
            }
        }

        static class Engineer extends Employee {
        }

        static class Salesman extends Employee {
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class ReflectionCase {
        //利用反射使得子类在工厂函数中得以完全隐藏
        static class Employee {
            public static Employee createEmployee(String className) {
                try {
                    return (Employee) Class.forName(className).newInstance();
                } catch (Exception e) {
                    throw new IllegalArgumentException("Unable to instantiate " + className);
                }
            }
        }

        static class Engineer extends Employee {
        }

        public static void main(String[] args) throws ClassNotFoundException {
//            String clsName = "com.filber.refactor._5_invocation._53_ReplaceConstructorWithFactoryMethod_329$ReflectionCase$Engineer";
            String clsName = Engineer.class.getName();
            Employee employee = Employee.createEmployee(clsName);
            System.out.println(employee);
        }
    }

    //-------------------------------------------------------------------------------------------------
    //以调用显式方法的方式创建子类实例
    static class ExplicitMethodCase {
        static class Person {
            public static Person createMale() {
                return new Male();
            }

            public static Person createFemale() {
                return new Female();
            }
        }

        static class Male extends Person {
        }

        static class Female extends Person {
        }
    }
}