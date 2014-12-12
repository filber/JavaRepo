package com.filber.refactor._1_method;

import java.util.Iterator;
import java.util.List;

/**
 * 提取函数.
 */
public class _01_ExtractMethod_135 {
    //-------------------------------------------------------------------------------------------------
    //Init Field.
	String _name;
	List<Order> _orders;
	static class Order{
		double amount;
		public double getAmount() {
			return amount;
		}
	}

    //-------------------------------------------------------------------------------------------------
    //待处理的原函数.
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
    //1.提取出不带参数的函数
	private void printBanner(){
		System.out.println("********************");
		System.out.println("*****Customer Owes*****");
		System.out.println("********************");
	}
    //2.提取出带参数的函数
	private void printDetails(double outstanding){
		System.out.println("name:"+_name);
		System.out.println("outstanding:"+outstanding);
	}
    //3.提取出带参数与返回值的函数
	private double calOutstanding(double initAmount){
		Iterator<Order> orderIterator= _orders.iterator();
		double resultAmount = initAmount;
		while (orderIterator.hasNext()) {
			Order each = orderIterator.next();
			resultAmount += each.getAmount();
		}
		return resultAmount;
	}
	//4.提取之后的原函数.
	void goodPrintOwing(double previousAmount){
		printBanner();
		double outstanding = calOutstanding(previousAmount * 1.2);
		printDetails(outstanding);
	}
}
