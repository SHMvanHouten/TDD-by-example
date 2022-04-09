package com.github.shmvanhouten.tddmoney;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO:
 * $5 + 10 CHF = $10 if rate is 2:1
 * $5 * 2 = $10
 * Make amount private
 * Dollar side-effects?
 * Money rounding?
 */
class MoneyExchangeTest {

    @Test
    void testMultiplication() {
        Dollar five = new Dollar(5);
        five.times(2);
        assertEquals(10, five.amount);
    }

}