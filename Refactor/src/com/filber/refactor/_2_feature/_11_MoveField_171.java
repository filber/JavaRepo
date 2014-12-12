package com.filber.refactor._2_feature;


public class _11_MoveField_171 {
	class BadAccount{
		BadAccountType _type;
		//语义层面来说,_interestRate与AccountType的关联更紧密.
		double _interestRate;
		double interestForAmountDays(double amount,int days) {
			return _interestRate*amount*days/365;
		}
	}
	class BadAccountType{
	}
	
	class GoodAccount{
		GoodAccountType _type;
		double interestForAmountDays(double amount,int days) {
			return getInterestRate()*amount*days/365;
		}
		//Self Encapsulate Field:自封装值域,相当于对field的一个delegation.
		public double getInterestRate() {
			return _type.getInterestRate();
		}
	}
	class GoodAccountType{
		double _interestRate;
		public double getInterestRate(){
			return _interestRate;
		}
		public void setInterestRate(double _interestRate){
			this._interestRate = _interestRate;
		}
	}
}
