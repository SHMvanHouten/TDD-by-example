package com.github.shmvanhouten.tddmoney;

public abstract class Money {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public abstract Money times(int amount);

    public String currency() {
        return this.currency;
    };

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return this.amount == money.amount
                && this.getClass() == money.getClass();
    }
}
