package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.blackjack.card.Card;
import ru.nsu.fit.oop.ilg.blackjack.card.Rank;
import ru.nsu.fit.oop.ilg.blackjack.card.Suit;

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
    void toStringShouldContainRussianRankAndSuit() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals("Туз Черви", card.toString());
    }
}