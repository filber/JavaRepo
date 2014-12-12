package com.filber.refactor._3_field;

/**
 * Created by Administrator on 2014/12/2.
 */
public class _31_ReplaceTypeCodeWithSubclasses_248 {

    //类型码会影响宿主类的行为,则使用Replace Type Code with Subclasses来处理它们
    //为Replace Conditional with Polymorphism做准备

    static class OldEmployee{
        private int _type;
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
        OldEmployee(int type){
            this._type=type;
        }
        public int getType(){
            return _type;
        }
        public int getSalary(){
            if (_type==ENGINEER){
                return 100;
            } else if (_type==SALESMAN){
                return 200;
            } else if (_type==MANAGER){
                return 300;
            } else {
                return 0;
            }
        }
    }

    //去除了_type字段
    // 将根据_type判断采取不同动作的行为getSalary放在了子类中实现.
    static abstract class Employee{
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
        //FactoryMethod根据不同的类型码返回对应的子类.
        public static Employee createEmployee(int type){
            if (type==ENGINEER){
                return new Engineer();
            } else if(type==SALESMAN){
                return new Salesman();
            } else if (type==MANAGER){
                return new Manager();
            } else {
                throw new IllegalArgumentException();
            }
        }
        //常量函数-Constant Method
        public abstract int getSalary();
    }
    static class Engineer extends Employee{
        @Override
        public int getSalary() {
            return 100;
        }
    }
    static class Salesman extends Employee{
        @Override
        public int getSalary() {
            return 200;
        }
    }
    static class Manager extends Employee{
        @Override
        public int getSalary() {
            return 300;
        }
    }
}
