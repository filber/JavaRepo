package com.filber.refactor._3_field;

public class _18_SelfEncapsulateField_196 {
	
	class BadIntRange{
		public int _low,_hight;
		boolean includes(int arg){
			return arg>=_low&&arg<=_hight;
		}
		void grow(int factor){
			_hight=factor*_hight;
		}
	}
	
	class GoodIntRange {
		private int _low,_hight;
		public GoodIntRange(int _low, int _hight) {
			//TODO 在构造函数中要谨慎处理Setter&Getter
			//因为Setter&Getter通常被认为对象创建之后才使用.
			//set_hight(_hight);
			//set_low(_low);
			this._low=_low;
			this._hight=_hight;
		}
		boolean includes(int arg){
			return arg>=get_low()&&arg<=get_hight();
		}
		void grow(int factor){
			set_hight(factor*get_hight());
		}
		public int get_low() {
			return _low;
		}
		public void set_low(int _low) {
			this._low = _low;
		}
		public int get_hight() {
			return _hight;
		}
		public void set_hight(int _hight) {
			this._hight = _hight;
		}
	}
	
	class CappedRange extends GoodIntRange {
		public CappedRange(int _low, int _hight,int _cap) {
			super(_low, _hight);
			this._cap=_cap;
		}
		private int _cap;
		public int get_cap() {
			return _cap;
		}
		public int get_hight(){
			return Math.min(super.get_hight(), get_cap());
		}
	}
}