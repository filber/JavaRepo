package com.filber.refactor._7_huge;

public class _72_ExtractHierarchy_400 {
    class BadCase{
        class BillingScheme{
            private boolean isDisabilityScheme(){
                if (true) return true;
                else return false;
            }
            public void createSchema(){
                if (isDisabilityScheme()){
                    //do something
                } else {
                    //xxxx
                }
            }
        }
    }

    class GoodCase{
        abstract class BillingScheme{
            public abstract void createSchema();
        }
        class DisableBillingScheme extends BillingScheme{
            public void createSchema() {
                //do something
            }
        }
    }
}
