package com.filber.refactor._3_field;

/**
 * 以字面常量取代魔法数
 */
public class _26_ReplaceMagicNumberWithSymbolicConstant_229 {
    class BadCase {
        double potentialEnergy(double mass, double height) {
            return mass * 9.81 * height;
        }
    }

    class GoodCase {
        double potentialEnergy(double mass, double height) {
            return mass * GRAVATATIONAL_CONSTANT * height;
        }

        static final double GRAVATATIONAL_CONSTANT = 9.81;
    }
}
