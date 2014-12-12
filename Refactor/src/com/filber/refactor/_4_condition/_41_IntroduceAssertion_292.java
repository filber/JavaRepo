package com.filber.refactor._4_condition;

/**
 * Created by Administrator on 2014/12/4.
 */
public class _41_IntroduceAssertion_292 {
    public static void testCase(){
        //对虚拟机添加-ea启动参数,启用断言(-da禁用断言)
        //可以在正常情况下不会到达的地方放置断言
        //将断言放在需要假设的地方帮助程序理解.
        assert 1>2:"HELLO";
    }

    public static void main(String[] args) {
        try {
            testCase();
        }catch (AssertionError e){
            e.printStackTrace();
        }

    }
}
