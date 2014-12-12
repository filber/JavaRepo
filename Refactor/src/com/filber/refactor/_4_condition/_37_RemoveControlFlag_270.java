package com.filber.refactor._4_condition;

/**
 * Created by Administrator on 2014/12/3.
 */
public class _37_RemoveControlFlag_270 {

    private void sendAlert(){}

    public void badCheckSecurity(String[] people){
        boolean found = false;
        for (int i = 0; i < people.length; i++) {
            if (!found){
                if ("Don".equals(people[i])){
                    found=true;
                    sendAlert();
                }
                if ("John".equals(people[i])){
                    found=true;
                    sendAlert();
                }
            }
        }
    }
    //-------------------------------------------------------------------------------------------------
    private boolean isBadPerson(String person){
        return "Don".equals(person) || "John".equals(person);
    }
    //以break取代Control Flag
    public void breakCheckSecurity(String[] people){
        for (int i = 0; i < people.length; i++) {
            if (isBadPerson(people[i])){
                sendAlert();
                break;
            }
        }
    }
    //-------------------------------------------------------------------------------------------------

    private void someLaterCode(String person){}
    public void bad2CheckSecurity(String[] people){
        String found = "";
        for (int i = 0; i < people.length; i++) {
            if ("".equals(found)){
                if ("Don".equals(people[i])){
                    sendAlert();
                    found = "Don";
                }
                if ("John".equals(people[i])){
                    sendAlert();
                    found = "John";
                }
            }
        }
        someLaterCode(found);
    }

    //-------------------------------------------------------------------------------------------------

    private String foundMiscreant(String[] people){
        for (int i = 0; i < people.length; i++) {
            if ("Don".equals(people[i])||"John".equals(people[i])){
                sendAlert();
                return people[i];
            }
        }
        return "";
    }
    public void returnCheckSecurity(String[] people){
        String found = foundMiscreant(people);
        someLaterCode(found);
    }
}

