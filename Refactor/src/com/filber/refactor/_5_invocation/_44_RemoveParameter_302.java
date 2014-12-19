package com.filber.refactor._5_invocation;

import java.util.Date;

/**
 * 移除参数
 */
public class _44_RemoveParameter_302 {

    @Deprecated
    public void getContact(Date date) {
        getContact();
    }

    public void getContact() {

    }
}
