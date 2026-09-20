package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу перечисления Rank.
 */
public class RankTest {

    @Test
    void aceShouldHaveValue11() {
        assertEquals(11, Rank.ACE.getValue());
    }

    @Test
    void faceCardsShouldHaveValue10() {
        assertEquals(10, Rank.KING.getValue());
        assertEquals(10, Rank.QUEEN.getValue());
        assertEquals(10, Rank.JACK.getValue());
    }

    @Test
    void tenShouldHaveValue10() {
        assertEquals(10, Rank.TEN.getValue());
    }

    @Test
    void twoShouldHaveValue2() {
        assertEquals(2, Rank.TWO.getValue());
    }
}