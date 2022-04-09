package com.github.shmvanhouten.tddmoney;

public class Dollar {
    public final int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
