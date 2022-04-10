package com.github.shmvanhouten.tddmoney;

public class Sum implements Expression {
    final Expression augend;
    final Expression addend;

    public Sum(Expression augend, Expression addend) {

        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int amount = augend.reduce(bank, to).amount
                + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    @Override
    public Expression plus(Expression addend) {
        return null;
    }
}
