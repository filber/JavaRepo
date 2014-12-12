package com.filber.refactor._1_method;

public class _05_IntroduceExplainingVariable_149 {

	int _quantity;
	double _itemPrice;
	
	double badPrice(){
		//base price - quantity discount + shipping
		return _quantity*_itemPrice - Math.max(0, _quantity-500)*_itemPrice*0.05
				+ Math.min(_quantity*_itemPrice*0.1, 100.0);
	}

    //-------------------------------------------------------------------------------------------------

	//大部分情况下用ExtractMethod效果会更好
	//当ExtractMethod代价太大,无法直接实现时,可以用IntroduceExplainingVariable
	double goodPrice() {
		final double basePrice = _quantity*_itemPrice;
		final double quantityDiscount = Math.max(0, _quantity-500)*_itemPrice*0.05;
		final double shipping = Math.min(basePrice*0.1, 100.0);
		
		return basePrice + quantityDiscount - shipping;
	}
}
