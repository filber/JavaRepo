package com.filber.refactor._5_invocation;

/**
 * 重命名函数
 */
public class _42_RenameMethod_298 {

    class Person {
        private String officeAreaCode;
        private String officeNumber;

        // 将旧函数改为Delegate的方式引用新函数.
        @Deprecated
        public String getTelephoneNumber() {
            return getOfficeTelephoneNumber();
        }

        public String getOfficeTelephoneNumber() {
            return "(" + officeAreaCode + ")" + officeNumber;
        }
    }
}
