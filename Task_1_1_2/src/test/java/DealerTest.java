package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    @Test
    void dealerShouldTakeCardsUntilScoreIsAtLeast17() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        dealer.playTurn(deck);

        assertTrue(dealer.getScore() >= 17);
    }

    @Test
    void dealerShouldNotTakeCardWhenScoreIsAlready17() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        dealer.takeCard(new Card(Suit.HEARTS, Rank.TEN));
        dealer.takeCard(new Card(Suit.SPADES, Rank.SEVEN));

        int cardsBefore = dealer.getHand().getCards().size();

        dealer.playTurn(deck);

        int cardsAfter = dealer.getHand().getCards().size();

        assertEquals(cardsBefore, cardsAfter);
    }
}