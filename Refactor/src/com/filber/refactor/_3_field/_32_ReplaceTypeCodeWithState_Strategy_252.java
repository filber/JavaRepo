package com.filber.refactor._3_field;

/**
 * 以State/Strategy取代类型码
 */
public class _32_ReplaceTypeCodeWithState_Strategy_252 {
    // 1.类型码会影响宿主类的行为
    // 2.同时类型码运行期可变

    class BadCase {
        class Employee {
            public static final int ENGINEER = 0;
            public static final int SALESMAN = 1;
            public static final int MANAGER = 2;

            private int type;
            private int monthlySalary;
            private int commission;
            private int bonus;

            Employee(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public int getSalary() {
                if (type == ENGINEER) {
                    return monthlySalary;
                } else if (type == SALESMAN) {
                    return monthlySalary + commission;
                } else if (type == MANAGER) {
                    return monthlySalary + bonus;
                } else {
                    throw new RuntimeException("Incorrect Employee");
                }
            }

            public void setMonthlySalary(int monthlySalary) {
                this.monthlySalary = monthlySalary;
            }

            public void setCommission(int commission) {
                if (type != SALESMAN) throw new RuntimeException("Incorrect Employee");
                this.commission = commission;
            }

            public void setBonus(int bonus) {
                if (type != MANAGER) throw new RuntimeException("Incorrect Employee");
                this.bonus = bonus;
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        abstract class Salary {
            private int monthlySalary;

            public int getMonthlySalary() {
                return monthlySalary;
            }

            public void setMonthlySalary(int monthlySalary) {
                this.monthlySalary = monthlySalary;
            }

            public abstract int getPayAmount();
        }

        class EngineerSalary extends Salary {
            public int getPayAmount() {
                return super.getMonthlySalary();
            }
        }

        class SalesmanSalary extends Salary {
            private int commission;

            public int getCommission() {
                return commission;
            }

            public void setCommission(int _commission) {
                this.commission = _commission;
            }

            public int getPayAmount() {
                return super.getMonthlySalary() + getCommission();
            }
        }

        class ManagerSalary extends Salary {
            private int bonus;

            public int getBonus() {
                return bonus;
            }

            public void setBonus(int bonus) {
                this.bonus = bonus;
            }

            public int getPayAmount() {
                return super.getMonthlySalary() + getBonus();
            }
        }

        class Employee {
            private Salary salary;

            public Salary getSalary() {
                return salary;
            }

            //Making Salary Strategy can possibly be changed at runtime.
            public void setSalary(Salary salary) {
                this.salary = salary;
            }

            //Delegate Method
            public int getPayAmount() {
                return salary.getPayAmount();
            }
        }
    }
}
