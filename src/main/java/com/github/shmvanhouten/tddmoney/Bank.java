package com.github.shmvanhouten.tddmoney;

import java.util.Hashtable;

public class Bank {

    private final Hashtable<Pair, Integer> rates = new Hashtable<>();

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String from, String to, int amount) {
        rates.put(new Pair(from, to), amount);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        else return rates.get(new Pair(from, to));
    }
}
