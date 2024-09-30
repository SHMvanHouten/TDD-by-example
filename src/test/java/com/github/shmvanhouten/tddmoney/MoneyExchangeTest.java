package com.github.shmvanhouten.tddmoney;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * TODO:
 // * $5 + 10 CHF = $10 if rate is 2:1
 // * $5 + $5 = $10
 // * Return Money from $5 + $5 <- did not do
 // * Bank.reduce(Money)
 // * Reduce money with conversion
 // * Reduce(Bank, String)
 * Money rounding?
 * hashcode()
 * Equal null
 * Equal object
 // * Sum.plus
 // * Expression.times
 */
class MoneyExchangeTest {

    @Test
    void multiplication() {
        Money five = Money.dollar(5);
        assertThat(five.times(2), is(Money.dollar(10)));
        assertThat(five.times(3), is(Money.dollar(15)));
    }

    @Test
    void equality() {
        assertThat(Money.dollar(5), is(Money.dollar(5)));
        assertThat(Money.dollar(5), is(not(Money.dollar(6))));
        assertThat(Money.franc(5), is(not(Money.dollar(5))));
    }

    @Test
    void currency() {
        assertThat(Money.dollar(1).currency(), is("USD"));
        assertThat(Money.franc(1).currency(), is("CHF"));
    }

    @Test
    void simple_addition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(reduced, is(Money.dollar(10)));
    }

    @Test
    void plus_returns_sum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(sum.augend, is(five));
        assertThat(sum.addend, is(five));
    }

    @Test
    void reduce_sum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertThat(result, is(Money.dollar(7)));
    }

    @Test
    void reduce_money() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(result, is(Money.dollar(1)));
    }

    @Test
    void reduce_money_different_currency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertThat(result, is(Money.dollar(1)));
    }

    @Test
    void identity_rate() {
        assertThat(new Bank().rate("USD", "USD"), is(1));
    }

    @Test
    void mixed_addition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertThat(result, is(Money.dollar(10)));
    }

    @Test
    void sum_plus_money() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertThat(result, is(Money.dollar(15)));
    }

}