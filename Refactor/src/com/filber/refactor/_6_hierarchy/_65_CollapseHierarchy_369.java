package com.filber.refactor._6_hierarchy;

/**
 * 折叠继承体系
 */
public class _65_CollapseHierarchy_369 {
    //超类和子类并无太大区别

    class BadCase {
        class Employee {
        }

        class Salesman extends Employee {
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        class Employee {
        }
    }
}
