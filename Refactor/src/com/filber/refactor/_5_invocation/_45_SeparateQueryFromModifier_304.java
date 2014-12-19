package com.filber.refactor._5_invocation;

/**
 * 将查询函数和修改函数分离
 */
public class _45_SeparateQueryFromModifier_304 {

    private void sendAlert() {
    }

    public String badFoundMiscreant(String[] people) {
        for (int i = 0; i < people.length; i++) {//循环查询是一个读操作.
            if ("Don".equals(people[i]) || "John".equals(people[i])) {
                //该函数的调用是一个写操作.
                sendAlert();
                return people[i];
            }
        }
        return "";
    }

    //-------------------------------------------------------------------------------------------------
    // 提取出查询函数foundPerson
    // 可以重复调用但返回相同的结果.
    private String foundPerson(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if ("Don".equals(people[i]) || "John".equals(people[i])) {
                return people[i];
            }
        }
        return "";
    }

    // 提取出修改函数sendAlert
    //本方法声明为synchronized可以保证分别安全地依次调用foundPerson和sendAlert.
    public synchronized void sendAlert(String[] people) {
        if (!"".equals(foundPerson(people))) {
            sendAlert();
        }
    }
}
