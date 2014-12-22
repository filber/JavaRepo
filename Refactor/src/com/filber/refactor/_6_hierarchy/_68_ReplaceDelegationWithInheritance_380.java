package com.filber.refactor._6_hierarchy;

/**
 * 以继承取代委托
 */
public class _68_ReplaceDelegationWithInheritance_380 {
    //两个类之间存在太多的委托关系.

    class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return name.substring(name.lastIndexOf(' ') + 1);
        }
    }

    //------------------------------------------------------------------
    class DelegationCase {
        class Employee {
            private Person person;

            public Employee(Person person) {
                this.person = person;
            }

            public String getName() {
                return person.getName();
            }

            public void setName(String name) {
                person.setName(name);
            }

            public String toString() {
                return "Emp: " + person.getLastName();
            }
        }
    }

    //------------------------------------------------------------------
    class InheritanceCase {
        class Employee extends Person {
            public String toString() {
                return "Emp: " + getLastName();
            }
        }
    }

}
