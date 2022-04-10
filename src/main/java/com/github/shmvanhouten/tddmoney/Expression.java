package com.github.shmvanhouten.tddmoney;

public interface Expression {
    Money reduce(Bank bank, String to);

    Expression times(int multiplier);

    Expression plus(Expression addend);
}
