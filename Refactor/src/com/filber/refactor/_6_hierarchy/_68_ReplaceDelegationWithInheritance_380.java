package com.filber.refactor._6_hierarchy;

public class _68_ReplaceDelegationWithInheritance_380 {

    //Test Conflict.

    class Person{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getLastName(){
            return name.substring(name.lastIndexOf(' ')+1);
        }
    }

    class OldEmployee{
        private Person person;
        public OldEmployee(Person person) {
            this.person = person;
        }
        public String getName(){
            return person.getName();
        }
        public void setName(String name){
            person.setName(name);
        }
        public String toString() {
            return "Emp: "+person.getLastName();
        }
    }

    class Employee extends Person{
        public String toString() {
            return "Emp: "+getLastName();
        }
    }
}
