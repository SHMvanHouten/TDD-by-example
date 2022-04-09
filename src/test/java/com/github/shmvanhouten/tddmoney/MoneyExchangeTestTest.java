package com.github.shmvanhouten.tddmoney;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO:
 * $5 + 10 CHF = $10 if rate is 2:1
// * $5 * 2 = $10
 * Make amount private
// * Dollar side-effects?
 * Money rounding?
// * equals()
 * hashcode()
 */
class MoneyExchangeTest {

    @Test
    void testMultiplication() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        assertEquals(10, product.amount);
        product = five.times(3);
        assertEquals(15, product.amount);
    }

    @Test
    void testEquality() {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }
}