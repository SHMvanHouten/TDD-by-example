package com.github.shmvanhouten.tddmoney;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO:
 * $5 + 10 CHF = $10 if rate is 2:1
// * $5 * 2 = $10
// * Make amount private
// * Dollar side-effects?
 * Money rounding?
// * equals()
 * hashcode()
 * Equal null
 * Equal object
// * 5 CHF * 2 = 10 CHF
 * Dollar/Franc duplication
// * Common equals
 * common times
// * Compare Francs with Dollars
 * Currency?
 * delete testFrancMultiplication?
 */
class MoneyExchangeTest {

    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }
}