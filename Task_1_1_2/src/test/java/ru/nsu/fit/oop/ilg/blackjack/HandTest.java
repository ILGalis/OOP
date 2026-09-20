package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу класса Hand.
 */
public class HandTest {

    @Test
    void newHandShouldHaveZeroScore() {
        Hand hand = new Hand();

        assertEquals(0, hand.getScore());
    }

    @Test
    void shouldCalculateNormalCardsScore() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.TEN));
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));

        assertEquals(17, hand.getScore());
    }

    @Test
    void aceShouldCountAsOneWhenScoreExceeds21() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertEquals(16, hand.getScore());
    }

    @Test
    void shouldDetectBlackjack() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));

        assertTrue(hand.isBlackjack());
    }

    @Test
    void threeCardsWith21ShouldNotBeBlackjack() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));

        assertFalse(hand.isBlackjack());
    }

    @Test
    void shouldDetectBust() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertTrue(hand.isBust());
    }
}