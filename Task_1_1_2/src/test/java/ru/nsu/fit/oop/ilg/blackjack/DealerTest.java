package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.blackjack.card.Card;
import ru.nsu.fit.oop.ilg.blackjack.card.Deck;
import ru.nsu.fit.oop.ilg.blackjack.card.Rank;
import ru.nsu.fit.oop.ilg.blackjack.card.Suit;
import ru.nsu.fit.oop.ilg.blackjack.player.Dealer;

/**
 * Проверяет работу класса Dealer.
 */
public class DealerTest {

    @Test
    void dealerShouldTakeInitialCards() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        dealer.takeInitialCards(deck);

        assertEquals(2, dealer.getHand().getCards().size());
    }

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