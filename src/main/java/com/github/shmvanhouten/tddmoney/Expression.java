package com.github.shmvanhouten.tddmoney;

public interface Expression {
    Money reduce(Bank bank, String to);
}
