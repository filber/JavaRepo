package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/5.
 */
public class _45_SeparateQueryFromModifier_304 {

    private void sendAlert(){}

    public String badFoundMiscreant(String[] people){
        for (int i = 0; i < people.length; i++) {
            if ("Don".equals(people[i])||"John".equals(people[i])){
                //该函数的调用是一个写动作.
                sendAlert();
                return people[i];
            }
        }
        return "";
    }

    //-------------------------------------------------------------------------------------------------

    //Query
    //foundPerson可以重复调用但返回相同的结果.
    private String foundPerson(String[] people){
        for (int i = 0; i < people.length; i++) {
            if ("Don".equals(people[i])||"John".equals(people[i])){
                return people[i];
            }
        }
        return "";
    }

    //Modifier
    //sendAlert是一个Modifier,与foundPerson区分开逻辑更加清楚.
    //本方法声明为synchronized可以保证分别安全地依次调用foundPerson和sendAlert.
    public synchronized void sendAlert(String[] people){
        if (!"".equals(foundPerson(people))){
            sendAlert();
        }
    }
}
