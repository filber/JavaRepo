package com.filber.refactor._5_invocation;

import java.util.Date;

/**
 * Created by Administrator on 2014/12/5.
 */
public class _44_RemoveParameter_302 {

    public void getContact(){

    }

    @Deprecated
    public void getContact(Date date){
        getContact();
    }
}
