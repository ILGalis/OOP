package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу класса Game.
 */
public class GameTest {

    @Test
    void newGameShouldHaveZeroWins() {
        Game game = new Game();

        assertEquals(0, game.getPlayerWins());
        assertEquals(0, game.getDealerWins());
    }
}