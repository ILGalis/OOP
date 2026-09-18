package ru.example.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу класса Body.
 */
public class BodyTest {

    @Test
    void newBodyShouldHaveEmptyHand() {
        Body body = new Body() {};

        assertNotNull(body.getHand());
        assertEquals(0, body.getHand().getCards().size());
    }

    @Test
    void bodyShouldBeAbleToTakeCard() {
        Body body = new Body() {};
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        body.takeCard(card);

        assertEquals(1, body.getHand().getCards().size());
        assertEquals(card, body.getHand().getCards().get(0));
    }

    @Test
    void bodyShouldReturnHandScore() {
        Body body = new Body() {};

        body.takeCard(new Card(Suit.HEARTS, Rank.TEN));
        body.takeCard(new Card(Suit.SPADES, Rank.SEVEN));

        assertEquals(17, body.getScore());
    }
}