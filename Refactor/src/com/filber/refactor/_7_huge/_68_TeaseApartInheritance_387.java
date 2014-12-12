package com.filber.refactor._7_huge;

public class _68_TeaseApartInheritance_387 {

    class BadCase{
        class Deal{}
        class ActiveDeal extends Deal{}
        class PassiveDeal extends Deal{}
        class TabularActiveDeal extends ActiveDeal{}
        class TabularPassiveDeal extends PassiveDeal{}
    }

    class GoodCase{
        class Deal{
            private PresentationStyle presentationStyle;
        }
        class ActiveDeal extends Deal{}
        class PassiveDeal extends Deal{}

        class PresentationStyle{}
        class SinglePresentationStyle extends PresentationStyle{}
        class TabularPresentationStyle extends PresentationStyle{}
    }
}
