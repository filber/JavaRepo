package com.filber.refactor._3_field;

/**
 * 将引用对象改为值对象
 */
public class _21_ChangeReferenceToValue_208 {

    static class ReferenceCurrency {
        private String code;

        private ReferenceCurrency(String code) {
            this.code = code;
        }

        //通过工厂方法取得实例
        public static ReferenceCurrency getCurrency(String code) {
            return new ReferenceCurrency(code);
        }

        public String getCode() {
            return code;
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class ValueCurrency {
        private final String code;

        public ValueCurrency(String code) {
            if (code == null || code.isEmpty()) throw new IllegalArgumentException();
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        //重写hashCode与equals!!
        //1.对于值对象,hashCode的重写规则一般是所有字段的^(异或运算)
        //field1.hashCode() ^ field2.hashCode()
        public int hashCode() {
            return code.hashCode();
        }

        //2.对于值对象,equals的重写规则一般是所有字段的equals返回值&(与运算)
        //filed1.equals(obj.field1) && field2==obj.field2
        public boolean equals(Object obj) {
            if (obj instanceof ValueCurrency) {
                ValueCurrency instance = (ValueCurrency) obj;
                return getCode().equals(instance.getCode());
            } else {
                return false;
            }
        }
    }
}