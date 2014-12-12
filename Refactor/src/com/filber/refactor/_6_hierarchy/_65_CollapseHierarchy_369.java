package com.filber.refactor._6_hierarchy;

public class _65_CollapseHierarchy_369 {
    class OldCase{
        class Employee{}
        class Salesman extends Employee{}
    }

    class NewCase{
        class Employee{}
    }
}
