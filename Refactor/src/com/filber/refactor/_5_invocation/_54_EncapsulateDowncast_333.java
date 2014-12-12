package com.filber.refactor._5_invocation;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class _54_EncapsulateDowncast_333 {
    static class Reading{}
    private Vector readings;

    public Object oldLastReading(){
        return readings.lastElement();
    }

    public Reading newLastReading(){
        //将DownCast封装在函数内部
        return (Reading) readings.lastElement();
    }
}
