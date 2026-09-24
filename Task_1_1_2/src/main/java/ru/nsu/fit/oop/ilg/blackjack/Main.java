package ru.nsu.fit.oop.ilg.blackjack;

import ru.nsu.fit.oop.ilg.blackjack.game.Game;

/**
 * Точка входа.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}