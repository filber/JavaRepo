package com.filber.refactor._6_hierarchy;

public class _59_PullUpConstructorBody_350 {
    class OldCase{
        class Employee{
            String name;
            String id;
        }
        class Manager extends Employee{
            private int grade;
            Manager(String name, String id, int grade) {
                this.name = name;
                this.id = id;
                this.grade = grade;
                if (isPrivileged())assignCar();
            }
            boolean isPrivileged(){
                return grade>4;
            }
            void assignCar(){}
        }
    }

    class NewCase{
        class Employee{
            private String name;
            private String id;
            Employee(String name, String id) {
                this.name = name;
                this.id = id;
            }
            void initialize(){
                if (isPrivileged())assignCar();
            }
            //一个默认实现
            boolean isPrivileged() {
                return false;
            }
            void assignCar(){}
        }
        class Manager extends Employee{
            private int grade;
            Manager(String name, String id, int grade) {
                super(name, id);
                this.grade = grade;
                initialize();
            }
            boolean isPrivileged(){
                return grade>4;
            }
        }
    }
}
