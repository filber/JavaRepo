package com.filber.refactor._7_huge;

public class _71_SeparateDomainFromPresentation_395 {
    class BadCase{
        class OrderWindow{}
    }

    class GoodCase{
        class OrderWindow{}
        //Introduce Domain Object.
        //Separate Domain From Presentation.
        class Order{}
        class OrderLine{}
    }
}
