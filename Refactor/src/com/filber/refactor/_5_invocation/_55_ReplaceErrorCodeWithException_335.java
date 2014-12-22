package com.filber.refactor._5_invocation;

/**
 * 以异常取代错误码
 */
public class _55_ReplaceErrorCodeWithException_335 {
    //非受控异常(Unchecked Exception):调用者有责任检查避免这种异常,如果抛出则表明这是一个"编程错误"

    //受控异常(Checked Exception):调用者负责捕获或处理这种异常,如果抛出则表明这是一个"逻辑错误"

    //------------------------------------------------------------------
    private int balance;

    private void handleOverDrawn() {
    }

    private void doTheUsualThing() {
    }

    class OldCase {
        public int withdraw(int amount) {
            if (amount > balance) {
                return -1;
            } else {
                balance -= amount;
                return 0;
            }
        }

        public void clientInvoke(int amount) {
            if (withdraw(amount) == -1) handleOverDrawn();
            else doTheUsualThing();
        }
    }

    //-------------------------------------------------------------------------------------------------
    class UncheckedExceptionCase {
        public void withdraw(int amount) {
            if (amount > balance) {
                throw new IllegalArgumentException("Amount too large.");
            } else {
                balance -= amount;
            }
        }

        private boolean canWithDraw(int amount) {
            return amount <= balance;
        }

        public void clientInvoke(int amount) {
            //Client负责检查保证不会触发UncheckedException.
            if (canWithDraw(amount)) {
                withdraw(amount);
                doTheUsualThing();
            } else {
                handleOverDrawn();
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class CheckedExceptionCase {
        class BalanceException extends Exception {
        }

        public void withdraw(int amount) throws BalanceException {
            if (amount > balance) throw new BalanceException();
            else balance -= amount;
        }

        public void checkedClient(int amount) {
            //Client负责捕获或处理异常
            try {
                withdraw(amount);
                doTheUsualThing();
            } catch (BalanceException e) {
                handleOverDrawn();
            }
        }
    }
}
