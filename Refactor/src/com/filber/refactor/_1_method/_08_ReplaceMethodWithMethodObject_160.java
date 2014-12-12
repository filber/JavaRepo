package com.filber.refactor._1_method;

public class _08_ReplaceMethodWithMethodObject_160 {
	
	int delta(){
		return 0;
	}
	
	int badGamma(int inputVal,int quantity,int yearToDate) {
		int importantValue1 = (inputVal*quantity) + delta();
		int importantValue2 = (inputVal*yearToDate)+100;
		if ((yearToDate-importantValue1)>100) {
			importantValue2 -= 20;
		}
		int importantValue3 = importantValue2*7;
		return importantValue3 - 2* importantValue1;
	}
	
	
	int goodGamma(int inputVal,int quantity,int yearToDate) {
		return new Gamma(inputVal, quantity, yearToDate, this).compute();
	}
	
	
	//在MethodObject内进行重构.
	static class Gamma{
		int inputVal;
		int quantity;
		int yearToDate;
		_08_ReplaceMethodWithMethodObject_160 origin;
		
		public Gamma(int inputVal, int quantity, int yearToDate,
				_08_ReplaceMethodWithMethodObject_160 origin) {
			this.inputVal = inputVal;
			this.quantity = quantity;
			this.yearToDate = yearToDate;
			this.origin = origin;
		}

		private int calImportantValue2(int importantValue1){
			int importantValue2 = (inputVal*yearToDate)+100;
			if ((yearToDate-importantValue1)>100) {
				importantValue2 -= 20;
			}
			return importantValue2;
		}
		int compute(){
			//如果delta没有访问origin的额外数据,可以将其MoveMethod至Gamma.
			int importantValue1 = (inputVal*quantity) + origin.delta();
			int importantValue2 = calImportantValue2(importantValue1);
			return importantValue2*7 - importantValue1*2;	
		}
	}
}
