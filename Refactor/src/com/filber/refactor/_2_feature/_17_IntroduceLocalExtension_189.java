package com.filber.refactor._2_feature;

import java.util.Date;

public class _17_IntroduceLocalExtension_189 {

	//子类Extension
	//易于实现,需要在对象的创建时进行替换.
	//但容易产生新旧对象的多重引用,导致数据无法同步.
	class MfDateSub extends Date {
		private Date date;

		public MfDateSub(int year,int month,int day) {
			super(year,month,day);
		}
		
		public MfDateSub nextDate() {
			return new MfDateSub(this.getYear(), this.getMonth(), this.getDay()+1);
		}
	}
	
	//包装类Extension
	//难于扩展,对象创建之后也可以进行替换.
	//可以使得新旧对象得以同步,但需要实现被包装类的其他方法.
	class MfDateWrap {
		private Date _original;

		public MfDateWrap(Date _original) {
			this._original = _original;
		}
		
		public MfDateWrap nextDate() {
			return new MfDateWrap(new Date(_original.getYear(), _original.getMonth(), _original.getDay()+1));
		}
		
		//TODO 要实现所有被包装类的方法
		public long getTime(){
			return _original.getTime();
		}
		
		//该方法智能做到向上兼容,即接受Date或MfDateWrap都是可以的.
		//做到了向Client隐藏包装类存在.
		public boolean after(Date when) {
			return _original.after(when);
		}
		
		//完对对Client隐藏会带来如下问题,a.equals(b)失去交换律
		//MfDateWrap.equals(Date)为true
		//Date.equals(MfDateWrap)为false
		public boolean equals(Date obj) {
			return _original.equals(obj);
		}
		
		//用Overload的方式公开"我进行了包装"这一事实
		public boolean equalsDate(Date obj) {
			return _original.equals(obj);
		}
		public boolean equalsMfDate(MfDateWrap obj) {
			return obj instanceof MfDateWrap && _original.equals(obj._original);
		}
	}
}