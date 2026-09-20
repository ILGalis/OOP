package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Перечисление мастей карт.
 */
public enum Suit {
    HEARTS("Черви"),
    SPADES("Пики"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы");

    private final String russianName;

    /**
     * Создаёт масть.
     *
     * @param russianName русское название
     */
    Suit(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название.
     *
     * @return название
     */
    public String getRussianName() {
        return russianName;
    }
}