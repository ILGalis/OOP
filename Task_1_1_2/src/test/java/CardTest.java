package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверяет работу класса Card.
 */
public class CardTest {

    @Test
    void shouldCreateCardWithSuitAndRank() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    void toStringShouldContainRankAndSuit() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals("ACE of HEARTS", card.toString());
    }
}