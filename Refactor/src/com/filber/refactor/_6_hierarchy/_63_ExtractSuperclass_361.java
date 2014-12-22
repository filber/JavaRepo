package com.filber.refactor._6_hierarchy;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 提炼超类
 */
public class _63_ExtractSuperclass_361 {
    class BadCase {
        class Employee {
            Employee(String name, int annualCost, int id) {
                this.name = name;
                this.annualCost = annualCost;
                this.id = id;
            }

            private String name;
            private int annualCost;
            private int id;

            public String getName() {
                return name;
            }

            public int getAnnualCost() {
                return annualCost;
            }

            public int getId() {
                return id;
            }
        }

        class Department {
            Department(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public int getTotalAnnualCost() {
                Enumeration<Employee> enumeration = getStaff();
                int result = 0;
                while (enumeration.hasMoreElements()) {
                    Employee employee = enumeration.nextElement();
                    result += employee.getAnnualCost();
                }
                return result;
            }

            public Enumeration<Employee> getStaff() {
                return staff.elements();
            }

            public boolean addStaff(Employee employee) {
                return staff.add(employee);
            }

            private String name;
            private Vector<Employee> staff = new Vector<Employee>();
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class GoodCase {
        abstract class Party {
            private String name;

            Party(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public abstract int getAnnualCost();

            public abstract int headCount();
        }

        class Employee extends Party {
            Employee(String name, int annualCost, int id) {
                super(name);//Pull Up Constructor Body.
                this.annualCost = annualCost;
                this.id = id;
            }

            public int getAnnualCost() {
                return annualCost;
            }

            public int headCount() {
                return 1;
            }

            public int getId() {
                return id;
            }

            private int annualCost;
            private int id;
        }

        //此处使用了Composite模式,可以计算多个子部门的预算.
        class Department extends Party {
            Department(String name) {
                super(name);
            }

            public int getAnnualCost() {
                return PartyLoopCounter.HeadCounter.loopParty(getParty());
            }

            public int headCount() {
                return PartyLoopCounter.AnnualCostCounter.loopParty(getParty());
            }

            public Enumeration<Party> getParty() {
                return parties.elements();
            }

            public boolean addParty(Party party) {
                return parties.add(party);
            }

            private Vector<Party> parties = new Vector<Party>();
        }

        abstract static class PartyLoopCounter {

            public static final PartyLoopCounter HeadCounter = new HeadCounter();
            public static final PartyLoopCounter AnnualCostCounter = new AnnualCostCounter();

            public int loopParty(Enumeration<Party> enumeration) {
                int result = 0;
                while (enumeration.hasMoreElements()) {
                    Party party = enumeration.nextElement();
                    result += count(party);
                }
                return result;
            }

            protected abstract int count(Party party);
        }

        static class HeadCounter extends PartyLoopCounter {
            protected int count(Party party) {
                return party.headCount();
            }
        }

        static class AnnualCostCounter extends PartyLoopCounter {
            protected int count(Party party) {
                return party.getAnnualCost();
            }
        }
    }
}