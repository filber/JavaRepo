package com.filber.refactor._3_field;

/**
 * Created by Administrator on 2014/12/2.
 */
public class _32_ReplaceTypeCodeWithStrategy_252 {
    //类型码会影响宿主类的行为,同时类型码运行期可变,则使用Replace Type Code with Strategy来处理它们
    static class OldEmployee{

        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;

        private int _type;
        private int _monthlySalary;
        private int _commission;
        private int _bonus;
        OldEmployee(int type){
            this._type=type;
        }
        public int getType(){
            return _type;
        }
        public int getSalary(){
            if (_type==ENGINEER){
                return _monthlySalary;
            } else if (_type==SALESMAN){
                return _monthlySalary+_commission;
            } else if (_type==MANAGER){
                return _monthlySalary+_bonus;
            } else {
                throw new RuntimeException("Incorrect Employee");
            }
        }
    }

    static abstract class Salary{
        private int monthlySalary;
        public int getMonthlySalary(){
            return monthlySalary;
        }
        public void setMonthlySalary(int monthlySalary) {
            this.monthlySalary = monthlySalary;
        }
        public abstract int getPayAmount();
    }
    static class EngineerSalary extends Salary{
        @Override
        public int getPayAmount() {
            return super.getMonthlySalary();
        }
    }
    static class SalesmanSalary extends Salary{
        private int commission;
        public int getCommission() {
            return commission;
        }
        public void setCommission(int _commission) {
            this.commission = _commission;
        }
        @Override
        public int getPayAmount() {
            return super.getMonthlySalary()+getCommission();
        }
    }
    static class ManagerSalary extends Salary{
        private int bonus;
        public int getBonus() {
            return bonus;
        }
        public void setBonus(int bonus) {
            this.bonus = bonus;
        }
        @Override
        public int getPayAmount() {
            return super.getMonthlySalary()+getBonus();
        }
    }
    static class Employee{
        private Salary salary;
        public Salary getSalary() {
            return salary;
        }
        //Making Salary Strategy can be possibly changed at runtime.
        public void setSalary(Salary salary) {
            this.salary = salary;
        }
        //Delegate Method
        public int getPayAmount(){
            return salary.getPayAmount();
        }
    }
}
