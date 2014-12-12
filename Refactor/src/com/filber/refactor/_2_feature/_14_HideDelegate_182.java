package com.filber.refactor._2_feature;

public class _14_HideDelegate_182 {

	class Person{
		String _name;
		Department _department;
		public Department getDepartment() {
			return _department;
		}
		
		//Delegate Method
		//将getDepartment().getManager()通过本方法Hide Delegate
		public Person getManager(){
			return _department.getManager();
		}
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