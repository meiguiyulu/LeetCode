package leetcode;

import java.util.Arrays;

public class Bank {

    private int length;
    private long[] balance;

    public Bank(long[] balance) {
        length = balance.length;
        this.balance = new long[length];
        for (int i = 0; i < length; i++) {
            this.balance[i] = balance[i];
        }
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 <= 0 || account1 > length
                || account2 > length || account2 <= 0
                || balance[account1 - 1] < money) {
            return false;
        } else {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }
    }

    public boolean deposit(int account, long money) {
        if (account <= 0 || account > length) {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account <= 0 || account > length || balance[account - 1] < money) {
            return false;
        }
        balance[account - 1] -= money;
        return true;
    }
}
