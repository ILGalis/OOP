package ru.nsu.fit.oop.ilg.blackjack.game;

/**
 * Результат раунда.
 */
public enum RoundResult {
    PLAYER_WIN("Ты выиграл!"),
    DEALER_WIN("Ты проиграл."),
    DRAW("Ничья.");

    private final String message;

    RoundResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}