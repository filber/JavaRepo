package com.filber.refactor._6_hierarchy;

/**
 * 提炼子类
 */
public class _62_ExtractSubclass_355 {
    class Employee {
        private int rate;

        Employee(int rate) {
            this.rate = rate;
        }

        public int getRate() {
            return rate;
        }
    }

    //------------------------------------------------------------------
    class BadCase {
        class JobItem {
            private int unitPrice;
            private int quantity;
            private boolean isLabor;
            private Employee employee;

            JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
                this.unitPrice = unitPrice;
                this.quantity = quantity;
                this.isLabor = isLabor;
                this.employee = employee;
            }

            public int getUnitPrice() {
                //类中的某些特性只被一部分实例(而非全部实例)使用到.
                return isLabor() ? employee.getRate() : unitPrice;
            }

            public int getQuantity() {
                return quantity;
            }

            public boolean isLabor() {
                return isLabor;
            }

            public Employee getEmployee() {
                return employee;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        class JobItem {
            private int unitPrice;
            private int quantity;

            JobItem(int unitPrice, int quantity) {
                this.unitPrice = unitPrice;
                this.quantity = quantity;
            }

            public int getUnitPrice() {
                return unitPrice;
            }

            public int getQuantity() {
                return quantity;
            }
        }

        //通过提炼子类,去除了isLabor这个字段
        class LaborItem extends JobItem {
            private Employee employee;

            LaborItem(int unitPrice, int quantity, Employee employee) {
                super(unitPrice, quantity);
                this.employee = employee;
            }

            public int getUnitPrice() {
                return employee.getRate();
            }

            public Employee getEmployee() {
                return employee;
            }
        }
    }
}
