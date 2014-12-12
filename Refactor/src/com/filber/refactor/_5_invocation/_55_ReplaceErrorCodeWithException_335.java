package com.filber.refactor._5_invocation;

/**
 * Created by Administrator on 2014/12/9.
 */
public class _55_ReplaceErrorCodeWithException_335 {
    //非受控异常(Unchecked Exception):调用者有责任检查避免这种异常,如果抛出则表明这是一个"编程错误"

    //受控异常(Checked Exception):调用者负责捕获或处理这种异常,如果抛出则表明这是一个"逻辑错误"

    private int balance;
    private void handleOverDrawn(){}
    private void doTheUsualThing(){}

    public int oldWithdraw(int amount){
        if (amount>balance){
            return -1;
        }
        else{
            balance -= amount;
            return 0;
        }
    }
    public void oldClient(int amount){
        if (oldWithdraw(amount)==-1)handleOverDrawn();
        else doTheUsualThing();
    }

    //-------------------------------------------------------------------------------------------------
    //Unchecked Exception
    public void uncheckedWithdraw(int amount){
        if (amount>balance){
            throw new IllegalArgumentException("Amount too large.");
        }
        else{
            balance -= amount;
        }
    }
    private boolean canWithDraw(int amount){
        return amount<=balance;
    }
    public void uncheckedClient(int amount){
        //Client负责检查保证不会触发UncheckedException.
        if (canWithDraw(amount)){
            uncheckedWithdraw(amount);
            doTheUsualThing();
        } else {
            handleOverDrawn();
        }
    }


    //-------------------------------------------------------------------------------------------------
    static class BalanceException extends Exception{}

    //Unchecked Exception
    public void checkedWithdraw(int amount) throws BalanceException {
        if (amount>balance) throw new BalanceException();
        else balance -= amount;
    }

    public void checkedClient(int amount){
        //Client负责捕获或处理异常
        try {
            checkedWithdraw(amount);
            doTheUsualThing();
        } catch (BalanceException e) {
            handleOverDrawn();
        }
    }
}
