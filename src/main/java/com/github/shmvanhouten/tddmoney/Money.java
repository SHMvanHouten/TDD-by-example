package com.github.shmvanhouten.tddmoney;

public class Money {
    protected int amount;

    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return this.amount == money.amount
                && this.getClass() == money.getClass();
    }
}
