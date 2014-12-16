package com.filber.refactor._4_condition;

/**
 * 移除控制标记
 */
public class _37_RemoveControlFlag_270 {

    public void badCheckSecurity(String[] people) {
        boolean found = false;
        for (int i = 0; i < people.length; i++) {
            if (!found) {
                if ("Don".equals(people[i])) {
                    found = true;
                    sendAlert();
                }
                if ("John".equals(people[i])) {
                    found = true;
                    sendAlert();
                }
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    //以break取代Control Flag
    public void breakCheckSecurity(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if (isBadPerson(people[i])) {
                sendAlert();
                break;
            }
        }
    }

    private boolean isBadPerson(String person) {
        return "Don".equals(person) || "John".equals(person);
    }

    //-------------------------------------------------------------------------------------------------
    public void badReturnCheckSecurity(String[] people) {
        String found = "";
        for (int i = 0; i < people.length; i++) {
            if ("".equals(found)) {
                if ("Don".equals(people[i])) {
                    sendAlert();
                    found = "Don";
                }
                if ("John".equals(people[i])) {
                    sendAlert();
                    found = "John";
                }
            }
        }
        someLaterCode(found);
    }

    //-------------------------------------------------------------------------------------------------
    private String foundMiscreant(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if ("Don".equals(people[i]) || "John".equals(people[i])) {
                sendAlert();
                return people[i];
            }
        }
        return "";
    }

    public void goodReturnCheckSecurity(String[] people) {
        String found = foundMiscreant(people);
        someLaterCode(found);
    }

    private void sendAlert() {
    }

    private void someLaterCode(String person) {
    }
}

