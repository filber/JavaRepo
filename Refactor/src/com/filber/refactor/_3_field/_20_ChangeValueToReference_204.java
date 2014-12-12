package com.filber.refactor._3_field;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

//引用对象可以被多个对象所引用,保证对象的唯一性
public class _20_ChangeValueToReference_204 {

		interface Order{
			String getCustomer();
			void setCustomer(String customer);
		}
		
		class ValueCustomer{
			final String _name;
			public ValueCustomer(String _name) {
				this._name = _name;
			}
			public String getName() {
				return _name;
			}
		}
		class ValueOrder implements Order{
			private ValueCustomer customer;
			public ValueOrder(String customerName) {
				this.customer = new ValueCustomer(customerName);
			}
			public String getCustomer() {
				return customer.getName();
			}
			public void setCustomer(String customer) {
				this.customer = new ValueCustomer(customer);
			}
		}
		
		static class ReferenceCustomer{
			static final Dictionary<String, ReferenceCustomer> _instances;
			static{
				_instances= new Hashtable<String, ReferenceCustomer>();
				new ReferenceCustomer("George").store();
				new ReferenceCustomer("William").store();
				new ReferenceCustomer("Jack").store();
			}
			//<init>设置为了private,所有对于实例的获取都需要通过这个FactoryMethod.
			public static ReferenceCustomer getNamedCustomer(String name){
				return _instances.get(name);
			}
			
			String _name;
			private void store(){
				_instances.put(getName(), this);
			}
			private ReferenceCustomer(String _name) {
				this._name = _name;
			}
			public String getName() {
				return _name;
			}
		}
		
		class NewOrder implements Order{
			private ReferenceCustomer customer;
			public NewOrder(String customerName) {
				//下面这一行并没有编译错误,虽然ReferenceCustomer<init>的access_flag为private
//				this.customer = new ReferenceCustomer(customerName);
				this.customer=ReferenceCustomer.getNamedCustomer(customerName);
			}
			public String getCustomer() {
				return customer.getName();
			}
			public void setCustomer(String customerName) {
				this.customer =ReferenceCustomer.getNamedCustomer(customerName);
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