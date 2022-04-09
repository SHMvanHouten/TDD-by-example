package com.github.shmvanhouten.tddmoney;

public abstract class Money {
    protected int amount;

    public abstract Money times(int amount);

    public static Money franc(int amount) {
        return new Franc(amount);
    }

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return this.amount == money.amount
                && this.getClass() == money.getClass();
    }
}
