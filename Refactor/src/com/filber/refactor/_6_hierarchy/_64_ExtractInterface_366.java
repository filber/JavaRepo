package com.filber.refactor._6_hierarchy;

/**
 * Created by Administrator on 2014/12/10.
 */
public class _64_ExtractInterface_366 {
    class OldCase{
        class Employee{
            int getRate(){
                return 10;
            }
            boolean hasSpecialSkill(){
                return true;
            }
        }
        class Computer{
            int getRate(){
                return 15;
            }
            boolean hasSpecialSkill(){
                return false;
            }
        }
        double charge(Employee employee,int days){
            int base = employee.getRate()*days;
            if (employee.hasSpecialSkill())base*=1.5;
            return base;
        }
        double charge(Computer computer,int days){
            int base = computer.getRate()*days;
            if (computer.hasSpecialSkill())base*=1.5;
            return base;
        }
    }

    //-------------------------------------------------------------------------------------------------

    static class NewCase{
        interface Billable{
            int getRate();
            boolean hasSpecialSkill();
        }
        class Employee implements Billable{
            public int getRate(){
                return 10;
            }
            public boolean hasSpecialSkill(){
                return true;
            }
        }
        class Computer implements Billable{
            public int getRate(){
                return 15;
            }
            public boolean hasSpecialSkill(){
                return false;
            }
        }
        double charge(Billable billable,int days){
            int base = billable.getRate()*days;
            if (billable.hasSpecialSkill())base*=1.5;
            return base;
        }
    }
}
