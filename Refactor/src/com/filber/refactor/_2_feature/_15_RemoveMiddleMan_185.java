package com.filber.refactor._2_feature;

public class _15_RemoveMiddleMan_185 {
    class Person{
        String _name;
        Department _department;
        //让Client直接调用受托类.
        public Department getDepartment() {
            return _department;
        }

        //去除掉Middle Man
//        public Person getManager(){
//            return _department.getManager();
//        }
    }

    class Department{
        Person _manager;
        public Department(Person _manager) {
            this._manager = _manager;
        }
        public Person getManager() {
            return _manager;
        }
    }
}
