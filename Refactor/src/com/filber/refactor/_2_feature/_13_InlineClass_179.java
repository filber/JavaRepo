package com.filber.refactor._2_feature;

public class _13_InlineClass_179 {

	class BadPerson{
		String _name;
		TelephoneNumber telephoneNumber;
	}
	class TelephoneNumber{
		String _officeAreaCode;
		String _officeNumber;
		public String getTelephoneNumber() {
			return "("+_officeAreaCode+") "+_officeNumber;
		}
	}
	
	//将萎缩类TelephoneNumber塞进它的最频繁用户类Person中.
	class GoodPerson{
		String _name;
		String _officeAreaCode;
		String _officeNumber;
		public String getTelephoneNumber() {
			return "("+_officeAreaCode+") "+_officeNumber;
		}
	}
}
