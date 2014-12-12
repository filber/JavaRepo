package com.filber.refactor._5_invocation;

import java.util.Date;

/**
 * Created by Administrator on 2014/12/5.
 */
public class _43_AddParameter_300 {

    @Deprecated
    public void getContact(){
        getContact(null);
    }

    public void getContact(Date date){
    }
}