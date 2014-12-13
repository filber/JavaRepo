package com.filber.refactor._2_feature;

/**
 * 将类内联化
 */
public class _13_InlineClass_179 {
    class OldCase{
        class Person {
            String _name;
            //TelephoneNumber可能只被Person使用,是一个萎缩类.
            TelephoneNumber telephoneNumber;
        }
        class TelephoneNumber{
            String _officeAreaCode;
            String _officeNumber;
            public String getTelephoneNumber() {
                return "("+_officeAreaCode+") "+_officeNumber;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
	//修正方式1:将萎缩类TelephoneNumber的属性和方法内联至它的唯一用户类Person中.
    class NewCase1{
        class Person {
            String _name;
            String _officeAreaCode;
            String _officeNumber;
            public String getTelephoneNumber() {
                return "("+_officeAreaCode+") "+_officeNumber;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    //修正方式2:将萎缩类TelephoneNumber的作为内部类内联至唯一用户类Person中.避免外部访问.
    class NewCase2{
        class Person {
            String _name;
            TelephoneNumber telephoneNumber;
            class TelephoneNumber{
                String _officeAreaCode;
                String _officeNumber;
                public String getTelephoneNumber() {
                    return "("+_officeAreaCode+") "+_officeNumber;
                }
            }
        }
    }
}
