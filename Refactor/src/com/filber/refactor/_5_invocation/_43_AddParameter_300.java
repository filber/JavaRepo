package com.filber.refactor._5_invocation;

import java.util.Date;

/**
 * 添加参数
 */
public class _43_AddParameter_300 {

    @Deprecated
    public void getContact(){
        getContact(null);
    }

    public void getContact(Date date){
    }
}