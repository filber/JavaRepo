package com.filber.refactor._7_huge;

/**
 * 将领域和表述/显示分离
 */
public class _71_SeparateDomainFromPresentation_395 {

    class BadCase {
        class OrderWindow {
        }
    }
    //------------------------------------------------------------------
    //MVC最核心的价值:将用户界面代码和领域模型分离.
    class GoodCase {
        class OrderWindow {
        }

        //Introduce Domain Object.
        //Separate Domain From Presentation.
        class Order {
        }

        class OrderLine {
        }
    }
}
