package com.filber.refactor._2_feature;

public class _10_MoveMethod_167 {
	
	class BadAccount{
		BadAccountType _type;
		int _daysOverdrawn;
		//语义层面来说,overdraftCharge与AccountType的关联更紧密.
		double overdraftCharge() {
			if (_type.isPremium()) {
				double result = 10;
				if (_daysOverdrawn>7) {
					result += (_daysOverdrawn-7)*0.85;
				}
				return result;
			} else {
				return _daysOverdrawn*1.75;
			}
		}
		double bankCharge(){
			double result = 4.5;
			if (_daysOverdrawn>0) {
				result += overdraftCharge();
			}
			return result;
		}
	}
	class BadAccountType{
		boolean isPremium(){
			return false;
		}
	}
	
	class GoodAccount{
		GoodAccountType _type;
		int _daysOverdrawn;
		
		double bankCharge() {
			double result = 4.5;
			result += _type.overdraftCharge(_daysOverdrawn);
			return result;
		}
	}
	class GoodAccountType{
		boolean isPremium(){
			return false;
		}
		double overdraftCharge(int daysOverdrawn) {
			if (daysOverdrawn<=0) {
				return 0;
			}
			if (isPremium()) {
				double result = 10;
				if (daysOverdrawn>7) {
					result += (daysOverdrawn-7)*0.85;
				}
				return result;
			} else {
				return daysOverdrawn*1.75;
			}
		}
	}	
	
}
