package com.github.shmvanhouten.tddmoney;

import java.math.BigDecimal;
import java.util.Hashtable;

public class Bank {

    private final Hashtable<Pair, BigDecimal> rates = new Hashtable<>();

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String from, String to, int amount) {
        addRate(from, to, new BigDecimal(amount));
    }

    private void addRate(String from, String to, BigDecimal amount) {
        rates.put(new Pair(from, to), amount);
    }

    public BigDecimal rate(String from, String to) {
        if (from.equals(to)) return BigDecimal.ONE;
        else return rates.get(new Pair(from, to));
    }
}
