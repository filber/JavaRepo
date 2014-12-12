package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/5.
 */
public class _42_RenameMethod_298 {

    class Person{
        private String officeAreaCode;
        private String officeNumber;

        /**
         * 将旧函数改为Delegate的方式引用新函数.
         */
        @Deprecated
        public String getTelephoneNumber(){
            return getOfficeTelephoneNumber();
        }

        public String getOfficeTelephoneNumber(){
            return "("+officeAreaCode+")"+officeNumber;
        }
    }
}
