package com.filber.refactor._2_feature;

public class _12_ExtractClass_174 {

	class BadPerson{
		String _name;
		String _officeAreaCode;
		String _officeNumber;
		public String getTelephoneNumber(){
			return "("+_officeAreaCode+") "+_officeNumber;
		}
	}

    //将类中某些聚合的属性和行为提取为一个独立的Class.6
	class GoodPerson{
		String _name;
		TelephoneNumber telephoneNumber;

		public GoodPerson(TelephoneNumber telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
		
		//1.ChangeValueToReference
		public TelephoneNumber getTelephoneNumber() {
			return telephoneNumber;
		}
		public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
		
		//2.Unmodifiable
		public String getTelephoneNumberInfo() {
			return telephoneNumber.getTelephoneNumber();
		}
		
		//3.Cloneable
		@Deprecated
		public TelephoneNumber getTelephoneNumberClone() {
			return (TelephoneNumber) telephoneNumber.clone();
		}
	}
	class TelephoneNumber implements Cloneable{
		
		@Override
		protected Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return null;
			}
		}
		String _officeAreaCode;
		String _officeNumber;
		public String getTelephoneNumber() {
			return "("+_officeAreaCode+") "+_officeNumber;
		}
	}
}
