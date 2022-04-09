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
 * Common equals
 * common hashcode
 *
 */
class MoneyExchangeTest {

    @Test
    void testMultiplication() {
        Dollar five = new Dollar(5);
        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    void testFrancMultiplication() {
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }

    @Test
    void testEquality() {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }
}