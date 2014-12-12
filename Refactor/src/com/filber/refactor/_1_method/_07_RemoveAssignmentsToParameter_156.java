package com.filber.refactor._1_method;

public class _07_RemoveAssignmentsToParameter_156 {

	// 对参数赋值,混淆了inputVal的含义
	int badDiscount(int inputVal,int quantity,int yearToDate){
		if (inputVal>50) {
			inputVal -=2;
		}
		if (quantity>100) {
			inputVal -=1;
		}
		if (yearToDate>1000) {
			inputVal -= 5;
		}
		return inputVal;
	}

    //-------------------------------------------------------------------------------------------------
	// 建立临时变量用于存放返回结果.
	int goodDiscount(int inputVal,int quantity,int yearToDate){
		int result = inputVal;
		if (inputVal>50) {
			result -=2;
		}
		if (quantity>100) {
			result -=1;
		}
		if (yearToDate>1000) {
			result -= 5;
		}
		return result;
	}
}
