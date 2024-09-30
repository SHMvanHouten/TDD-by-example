package com.github.shmvanhouten.tddmoney;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money implements Expression {
    protected BigDecimal amount;
    protected String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = amount.setScale(3, RoundingMode.DOWN);
        this.currency = currency;
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount.multiply(new BigDecimal(multiplier)), currency);
    }

    public String currency() {
        return this.currency;
    };

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        BigDecimal rate = bank.rate(currency, to);
        return new Money(amount.divide(rate, RoundingMode.HALF_UP), to);
    }

    public static Money franc(BigDecimal amount) {
        return new Money(amount, "CHF");
    }

    public static Money franc(int amount) {
        return franc(new BigDecimal(amount));
    }

    public static Money dollar(int amount) {
        return dollar(new BigDecimal(amount));
    }

    public static Money dollar(BigDecimal amount) {
        return new Money(amount, "USD");
    }

    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return Objects.equals(this.amount, money.amount)
                && currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
