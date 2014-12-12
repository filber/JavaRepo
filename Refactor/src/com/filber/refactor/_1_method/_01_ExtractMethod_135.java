package com.filber.refactor._1_method;

import java.util.Iterator;
import java.util.List;

public class _01_ExtractMethod_135 {

	String _name;
	List<Order> _orders;
	
	static class Order{
		double amount;
		public double getAmount() {
			return amount;
		}
	}
	
	void badPrintOwing(double previousAmount){
		Iterator<Order> orderIterator= _orders.iterator();
		double outstanding = previousAmount * 1.2;
		// print banner
		System.out.println("********************");
		System.out.println("*****Customer Owes*****");
		System.out.println("********************");
		
		// calculate outstanding
		while (orderIterator.hasNext()) {
			Order each = orderIterator.next();
			outstanding += each.getAmount();
		}
		
		// print details
		System.out.println("name:"+_name);
		System.out.println("outstanding:"+outstanding);
	}

    //-------------------------------------------------------------------------------------------------


	private void printBanner(){
		System.out.println("********************");
		System.out.println("*****Customer Owes*****");
		System.out.println("********************");
	}
	private void printDetails(double outstanding){
		System.out.println("name:"+_name);
		System.out.println("outstanding:"+outstanding);
	}
	private double calOutstanding(double initAmount){
		Iterator<Order> orderIterator= _orders.iterator();
		double resultAmount = initAmount;
		while (orderIterator.hasNext()) {
			Order each = orderIterator.next();
			resultAmount += each.getAmount();
		}
		return resultAmount;
	}
	
	void goodPrintOwing(double previousAmount){
		printBanner();
		double outstanding = calOutstanding(previousAmount * 1.2);
		printDetails(outstanding);
	}
}
