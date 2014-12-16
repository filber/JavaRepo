package com.filber.refactor._4_condition;

/**
 * 合并条件表达式
 */
public class _35_ConsolidateConditionalExpression_265 {
    private int seniority;
    private int monthDisabled;
    private boolean isPartTime;

    public double badDisabilityAmount() {
        if (seniority < 2) return 0;
        if (monthDisabled > 12) return 0;
        if (isPartTime) return 0;
        return 1;
    }

    //-------------------------------------------------------------------------------------------------
    private boolean notEligibleForDisability() {
        return seniority < 2 || monthDisabled > 12 || isPartTime;
    }

    public double goodDisabilityAmount() {
        if (notEligibleForDisability()) {
            return 0;
        } else {
            return 1;
        }
    }

    //-------------------------------------------------------------------------------------------------
    public double badNestedCondition() {
        if (isPartTime) {
            if (seniority < 2) {
                return 0;
            }
        }
        return 0.5;
    }

    //------------------------------------------------------------------
    public double goodNestedCondition() {
        if (isPartTime && seniority < 2) {
            return 0;
        }
        return 0.5;
    }
}