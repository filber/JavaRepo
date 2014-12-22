package com.filber.refactor._7_huge;

/**
 * 提炼继承体系
 */
public class _72_ExtractHierarchy_400 {
    class BadCase {
        class BillingScheme {
            private boolean isDisabilityScheme() {
                if (true) return true;
                else return false;
            }

            private boolean isBusinessScheme() {
                if (true) return true;
                else return false;
            }

            private boolean isResidentialScheme() {
                if (true) return true;
                else return false;
            }

            public void createSchema() {
                if (isDisabilityScheme()) {
                    //do something
                } else if (isBusinessScheme()) {
                    //do something
                } else if (isResidentialScheme()) {
                    //do something
                } else {
                    //xxxx
                }
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        abstract class BillingScheme {
            public abstract void createSchema();
        }

        class DisableBillingScheme extends BillingScheme {
            public void createSchema() {
                //do something
            }
        }

        class BusinessBillingScheme extends BillingScheme {
            public void createSchema() {
                //do something
            }
        }

        class ResidentialBillingScheme extends BillingScheme {
            public void createSchema() {
                //do something
            }
        }
    }
}
