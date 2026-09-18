package ru.example.blackjack;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу класса Deck.
 */
public class DeckTest {

    @Test
    void newDeckShouldContain52Cards() {
        Deck deck = new Deck();

        int count = 0;

        while (count < 52) {
            assertNotNull(deck.takeCard());
            count++;
        }

        assertEquals(52, count);
    }

    @Test
    void deckShouldContainAllCards() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.takeCard());
        }
    }

    @Test
    void shuffleShouldWork() {
        Deck deck = new Deck();

        assertDoesNotThrow(deck::shuffle);
    }
}