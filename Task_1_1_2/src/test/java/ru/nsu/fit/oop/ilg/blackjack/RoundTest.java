package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.blackjack.game.Round;

/**
 * Тесты для класса {@link Round}.
 */
public class RoundTest {

    /**
     * Проверяет, что новый раунд создаёт игрока и дилера.
     */
    @Test
    void newRoundShouldCreatePlayerAndDealer() {
        Round round = new Round();

        assertNotNull(round.getPlayer());
        assertNotNull(round.getDealer());
    }

    /**
     * Проверяет, что старт раунда выдаёт по две карты игроку и дилеру.
     */
    @Test
    void startRoundShouldGiveTwoCardsToPlayerAndDealer() {
        Round round = new Round();

        round.startRound();

        assertEquals(2, round.getPlayer().getHand().getCards().size());
        assertEquals(2, round.getDealer().getHand().getCards().size());
    }

    /**
     * Проверяет, что игрок может взять дополнительную карту.
     */
    @Test
    void playerShouldBeAbleToTakeCard() {
        Round round = new Round();

        round.startRound();
        int cardsBefore = round.getPlayer().getHand().getCards().size();

        round.playerTakeCard();

        int cardsAfter = round.getPlayer().getHand().getCards().size();

        assertEquals(cardsBefore + 1, cardsAfter);
    }

    /**
     * Проверяет, что ход дилера выполняется без исключений.
     */
    @Test
    void dealerTurnShouldBeCalledWithoutException() {
        Round round = new Round();

        round.startRound();

        assertDoesNotThrow(round::dealerTurn);
    }
}