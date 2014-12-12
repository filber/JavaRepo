package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/9.
 */
public class _53_ReplaceConstructorWithFactoryMethod_329 {
    static class Employee{

    }

    public static Employee createEmployee(String className){
        try {
            return (Employee) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to instantiate "+className);
        }
    }
}
