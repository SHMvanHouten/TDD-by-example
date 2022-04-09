package com.github.shmvanhouten.tddmoney;

public class Franc {
    private final int amount;

    public Franc(int amount) {
        this.amount = amount;
    }

    public Franc times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    public boolean equals(Object other) {
        Franc dollar = (Franc) other;
        return this.amount == dollar.amount;
    }
}
