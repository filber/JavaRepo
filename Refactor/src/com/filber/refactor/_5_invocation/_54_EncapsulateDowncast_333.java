package com.filber.refactor._5_invocation;

import java.util.Vector;

/**
 * 封装向下转型
 */
public class _54_EncapsulateDowncast_333 {
    class Reading {
    }

    private Vector readings;

    class BadCase {
        public Object lastReading() {
            return readings.lastElement();
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        public Reading lastReading() {
            //将DownCast封装在函数内部
            return (Reading) readings.lastElement();
        }
    }

    //-------------------------------------------------------------------------------------------------
    class TemplateCase {
        private Vector<Reading> readings;

        public Reading lastReading() {
            return readings.lastElement();
        }
    }
}
