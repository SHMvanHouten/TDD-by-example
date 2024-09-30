package com.github.shmvanhouten.tddmoney;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO:
 * // * $5 + 10 CHF = $10 if rate is 2:1
 * // * $5 + $5 = $10
 * // * Return Money from $5 + $5 <- did not do
 * // * Bank.reduce(Money)
 * // * Reduce money with conversion
 * // * Reduce(Bank, String)
 * Money rounding?
 * 3 decimal points
 * roundingMode?
 * hashcode()
 * Equal null
 * Equal object
 * // * Sum.plus
 * // * Expression.times
 */
class MoneyExchangeTest {

    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertThat(five.times(2), is(Money.dollar(10)));
        assertThat(five.times(3), is(Money.dollar(15)));
    }

    @Test
    void testEquality() {
        assertThat(Money.dollar(5), is(Money.dollar(5)));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testCurrency() {
        assertThat(Money.dollar(1).currency(), is("USD"));
        assertThat(Money.franc(1).currency(), is("CHF"));
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(reduced, is(Money.dollar(10)));
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(sum.augend, is(five));
        assertThat(sum.addend, is(five));
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertThat(result, is(Money.dollar(7)));
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(result, is(Money.dollar(1)));
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertThat(result, is(Money.dollar(1)));
    }

    @Test
    void testIdentityRate() {
        assertThat(new Bank().rate("USD", "USD"), is(BigDecimal.ONE));
    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertThat(result, is(Money.dollar(10)));
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertThat(result, is(Money.dollar(15)));
    }

    @Test
    void one_franc_is_half_a_dollar() {
        var oneFranc = Money.franc(1);
        var bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        Money result = bank.reduce(oneFranc, "USD");
        assertThat(result, is(Money.dollar(new BigDecimal("0.5"))));
    }

    @ParameterizedTest(name = "{0} francs at exchange rate {1} becomes {2} dollars")
    @CsvSource(value = {
            "1, 3, 0.333",
            "2, 3, 0.667"
    }
    )
    void rounds_to_3_decimal_points(
            int franc, int rate, BigDecimal expectedDollar
    ) {
        var oneFranc = Money.franc(franc);
        var bank = new Bank();
        bank.addRate("CHF", "USD", rate);

        Money result = bank.reduce(oneFranc, "USD");
        assertThat(result.amount, is(expectedDollar));
    }
}