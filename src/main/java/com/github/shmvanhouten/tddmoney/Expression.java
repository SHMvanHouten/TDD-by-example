package com.github.shmvanhouten.tddmoney;

public interface Expression {
    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);
}
