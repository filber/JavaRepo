package com.filber.refactor._1_method;

/**
 * 移除对参数的赋值.
 */
public class _07_RemoveAssignmentsToParameter_156 {
	int badDiscount(int inputVal,int quantity,int yearToDate){
        // 对参数inputVal赋值,混淆了inputVal的含义
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
	int goodDiscount(int inputVal,int quantity,int yearToDate){
        // 建立临时变量result用于存放返回结果.
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
