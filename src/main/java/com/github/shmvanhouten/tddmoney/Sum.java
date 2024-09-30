package com.github.shmvanhouten.tddmoney;

import java.math.BigDecimal;

public class Sum implements Expression {
    final Expression augend;
    final Expression addend;

    public Sum(Expression augend, Expression addend) {

        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        BigDecimal amount = augend.reduce(bank, to).amount
                .add(addend.reduce(bank, to).amount);
        return new Money(amount, to);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
}
