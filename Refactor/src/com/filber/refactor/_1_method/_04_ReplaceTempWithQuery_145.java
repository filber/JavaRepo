package com.filber.refactor._1_method;

/**
 * 以查询取代临时变量
 */
public class _04_ReplaceTempWithQuery_145 {
	int _quantity;
	int _itemPrice;
	
	double badGetPrice(){
		int basePrice = _quantity*_itemPrice;
		double discountFactor;
		if (basePrice>1000) {
			discountFactor = 0.95;
		} else {
			discountFactor = 0.98;
		}
		return basePrice*discountFactor;
	}
	//-------------------------------------------------------------------------------------------------
	private int basePrice(){
		return _quantity*_itemPrice;
	}
	//Extractc出discountFactor函数
    private double discountFactor(){
        //用basePrice()作为Query取代原先的临时变量.
		if (basePrice()>1000) {
			return 0.95;
		} else {
			return 0.98;
		}
	}
	double GoodGetPrice() {
		return basePrice()*discountFactor();
	}
}
