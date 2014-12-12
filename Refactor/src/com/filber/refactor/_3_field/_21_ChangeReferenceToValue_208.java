package com.filber.refactor._3_field;

//没有Setter,不可变(Immutable)
public class _21_ChangeReferenceToValue_208 {

	static class OldCurrency{
		//通过本工厂方法取得实例
		public static OldCurrency getCurrency(String code) {
			return new OldCurrency(code);
		}

		private String _code;
		//<init>为private,不可直接创建实例
		private OldCurrency(String code){
			_code = code;
		}
		public String getCode(){
			return _code;
		}
	}
	
	class NewCurrency{
		private String _code;

		//<init>为public,可直接创建实例
		public NewCurrency(String _code) {
			this._code = _code;
		}

        //只有Getter,没有Setter
        public String getCode(){
			return _code;
		}
		
		//TODO 对于值对象,hashCode的重写规则一般是所有字段的异或运算结果.
		//field1.hashCode() ^ field2.hashCode()
		public int hashCode() {
			return _code.hashCode();
		}

		//TODO 对于值对象,equals的重写规则一般为
		//filed1.equals(obj.field1) && field2.equals(obj.field2)
		public boolean equals(Object obj) {
			if(obj instanceof NewCurrency) {
				NewCurrency instance = (NewCurrency)obj;
				return getCode().equals(instance.getCode());
			} else {
				return false;
			}
		}
		
	}
}