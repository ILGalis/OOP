package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuitTest {

    @Test
    void shouldContainFourSuits() {
        assertEquals(4, Suit.values().length);
    }

    @Test
    void shouldContainAllSuits() {
        assertTrue(java.util.Arrays.asList(Suit.values()).contains(Suit.HEARTS));
        assertTrue(java.util.Arrays.asList(Suit.values()).contains(Suit.SPADES));
        assertTrue(java.util.Arrays.asList(Suit.values()).contains(Suit.DIAMONDS));
        assertTrue(java.util.Arrays.asList(Suit.values()).contains(Suit.CLUBS));
    }
}