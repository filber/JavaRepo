package com.filber.refactor._6_hierarchy;

public class _59_PullUpConstructorBody_350 {
    class BadCase {
        class Employee {
            String name;
            String id;
        }

        class Manager extends Employee {
            private int grade;

            Manager(String name, String id, int grade) {
                this.name = name;
                this.id = id;
                this.grade = grade;
                if (isPrivileged()) assignCar();
            }

            boolean isPrivileged() {
                return grade > 4;
            }

            void assignCar() {
            }
        }

        class Engineer extends Employee {
            private int techLevel;
            Engineer(String name, String id, int techLevel) {
                this.name = name;
                this.id = id;
                this.techLevel = techLevel;
                if (isPrivileged()) assignCar();
            }

            boolean isPrivileged() {
                return techLevel > 9;
            }

            void assignCar() {
            }
        }
    }

    //------------------------------------------------------------------
    //将构造器中相同的部分上移
    class GoodCase {
        class Employee {
            private String name;
            private String id;

            Employee(String name, String id) {
                this.name = name;
                this.id = id;
            }

            protected void initialize() {
                if (isPrivileged()) assignCar();
            }

            //一个默认实现
            protected boolean isPrivileged() {
                return false;
            }

            private void assignCar() {
            }
        }

        class Manager extends Employee {
            private int grade;

            Manager(String name, String id, int grade) {
                super(name, id);
                this.grade = grade;
                initialize();
            }

            protected boolean isPrivileged() {
                return grade > 4;
            }
        }

        class Engineer extends Employee {
            private int techLevel;

            Engineer(String name, String id, int techLevel) {
                super(name, id);
                this.techLevel = techLevel;
                initialize();
            }

            protected boolean isPrivileged() {
                return techLevel > 9;
            }
        }
    }
}
