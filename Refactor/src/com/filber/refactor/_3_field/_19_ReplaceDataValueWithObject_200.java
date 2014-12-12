package com.filber.refactor._3_field;

import java.util.Collection;
import java.util.Iterator;

//对数据项产生了额外的行为
//Duplicate Code
//Feature Envy
public class _19_ReplaceDataValueWithObject_200 {

		interface Order{
			String getCustomer();
			void setCustomer(String customer);
		}
		class OldOrder implements Order{
			public OldOrder(String customer) {
				this._customer = customer;
			}
			public String getCustomer() {
				return _customer;
			}
			public void setCustomer(String customer) {
				this._customer = customer;
			}
			String _customer;
		}
		
		//Customer为值对象,本身是没有Setter的
		//可以额外追加行为,但不适于追加信用等级,地址之类的属性.
		//使用ChangeValueToReference才可以.
		class Customer{
			final String _name;
			public Customer(String _name) {
				this._name = _name;
			}
			public String getName() {
				return _name;
			}
			//值对象一般需要重写hashCode和equals
			public int hashCode() {
				return _name.hashCode();
			}
			//值对象的同一性体现于内部值是否相等,而非是否是一个对象.
			public boolean equals(Object obj) {
				return obj instanceof Customer&& getName().equals(((Customer)obj).getName());
			}
		}
		class GoodOrder implements Order{
			private Customer customer;
			public String getCustomer() {
				return customer.getName();
			}
			public void setCustomer(String customer) {
				this.customer = new Customer(customer);
			}
		}
		
		static int numberOfOrdersFor(Collection<Order> orders, String customer){
			int result = 0;
			Iterator<Order> orderIterator = orders.iterator();
			while (orderIterator.hasNext()) {
				Order order = orderIterator.next();
				if (order.getCustomer().equals(customer)) {
					result ++;
				}
			}
			return result;
		}
}