package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    @Test
    void newRoundShouldCreatePlayerAndDealer() {
        Round round = new Round();

        assertNotNull(round.getPlayer());
        assertNotNull(round.getDealer());
    }

    @Test
    void startRoundShouldGiveTwoCardsToPlayerAndDealer() {
        Round round = new Round();

        round.startRound();

        assertEquals(2, round.getPlayer().getHand().getCards().size());
        assertEquals(2, round.getDealer().getHand().getCards().size());
    }

    @Test
    void playerShouldBeAbleToTakeCard() {
        Round round = new Round();

        round.startRound();
        int cardsBefore = round.getPlayer().getHand().getCards().size();

        round.playerTakeCard();

        int cardsAfter = round.getPlayer().getHand().getCards().size();

        assertEquals(cardsBefore + 1, cardsAfter);
    }

    @Test
    void dealerTurnShouldBeCalledWithoutException() {
        Round round = new Round();

        round.startRound();

        assertDoesNotThrow(round::dealerTurn);
    }
}