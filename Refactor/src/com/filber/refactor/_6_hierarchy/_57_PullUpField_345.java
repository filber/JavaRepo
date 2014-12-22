package com.filber.refactor._6_hierarchy;

/**
 * 字段上移
 */
public class _57_PullUpField_345 {
    class BadCase {
        class Employee {
        }

        class Salesman {
            private String name;

            public String getSalesmanName() {
                return name;
            }
        }

        class Engineer {
            private String name;

            public String getEngineerName() {
                return name;
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        class Employee {
            private String name;

            public String getName() {
                return name;
            }
        }

        class Salesman {
        }

        class Engineer {
        }
    }
}
