package com.filber.refactor._2_feature;

/**
 * 提炼类
 */
public class _12_ExtractClass_174 {

    class OldCase{
        class Person {
            String name;
            //电话号码相关的属性和行为可能重复出现在了多个类中.
            String officeAreaCode;
            String officeNumber;
            public String getTelephoneNumber(){
                return "("+ officeAreaCode +") "+ officeNumber;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class NewCase{
        class GoodPerson{
            String _name;
            TelephoneNumber telephoneNumber;

            public GoodPerson(TelephoneNumber telephoneNumber) {
                this.telephoneNumber = telephoneNumber;
            }
            //有三种方式可以向Client公开这个新增类.
            //1.返回引用,外部可以变更新对象.
            public TelephoneNumber getTelephoneNumber() {
                return telephoneNumber;
            }
            //2.使用HideDelegate,外部不知道晓新对象.
            public String getTelephoneNumberInfo() {
                return telephoneNumber.getTelephoneNumber();
            }
            //3.使用Clone,外部知晓新对象,但得到的是克隆副本
            //也可以将TelephoneNumber重构为值对象避免其被变更.
            public TelephoneNumber getTelephoneNumberClone() {
                return (TelephoneNumber) telephoneNumber.clone();
            }
        }
        class TelephoneNumber implements Cloneable{
            String _officeAreaCode;
            String _officeNumber;
            public String getTelephoneNumber() {
                return "("+_officeAreaCode+") "+_officeNumber;
            }
            protected Object clone() {
                try {
                    return super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
