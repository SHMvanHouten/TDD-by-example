package com.github.shmvanhouten.tddmoney;

public class Dollar {
    private final int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

    @Override
    public boolean equals(Object other) {
        Dollar dollar = (Dollar) other;
        return this.amount == dollar.amount;
    }
}
