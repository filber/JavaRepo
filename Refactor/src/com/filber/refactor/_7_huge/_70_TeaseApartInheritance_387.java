package com.filber.refactor._7_huge;

/**
 * 梳理并分解继承体系
 */
public class _70_TeaseApartInheritance_387 {

    class BadCase{
        //Deal<--ActiveDeal&PassiveDeal
        //ActiveDeal<--TabularActiveDeal
        //PassiveDeal<--TabularPassiveDeal
        class Deal{}
        class ActiveDeal extends Deal{}
        class PassiveDeal extends Deal{}
        class TabularActiveDeal extends ActiveDeal{}
        class SingleActiveDeal extends ActiveDeal{}
        class TabularPassiveDeal extends PassiveDeal{}
        class SinglePassiveDeal extends PassiveDeal{}
    }

    //------------------------------------------------------------------
    class GoodCase{
        //Deal<--ActiveDeal&PassiveDeal
        class Deal{
            private PresentationStyle presentationStyle;
        }
        class ActiveDeal extends Deal{}
        class PassiveDeal extends Deal{}

        //PresentationStyle<--SingleStyle&TabularStyle+
        class PresentationStyle{}
        class SingleStyle extends PresentationStyle{}
        class TabularStyle extends PresentationStyle{}
    }
}
