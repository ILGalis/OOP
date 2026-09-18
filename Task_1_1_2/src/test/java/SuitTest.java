package ru.example.blackjack;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для перечисления {@link Suit}.
 */
public class SuitTest {

    /**
     * Проверяет, что перечисление содержит четыре масти.
     */
    @Test
    void shouldContainFourSuits() {
        assertEquals(4, Suit.values().length);
    }

    /**
     * Проверяет, что перечисление содержит все ожидаемые масти.
     */
    @Test
    void shouldContainAllSuits() {
        assertTrue(Arrays.asList(Suit.values()).contains(Suit.HEARTS));
        assertTrue(Arrays.asList(Suit.values()).contains(Suit.SPADES));
        assertTrue(Arrays.asList(Suit.values()).contains(Suit.DIAMONDS));
        assertTrue(Arrays.asList(Suit.values()).contains(Suit.CLUBS));
    }
}