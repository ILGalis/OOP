package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Перечисление достоинств карт.
 */
public enum Rank {
    ACE(11, "Туз"),
    KING(10, "Король"),
    QUEEN(10, "Дама"),
    JACK(10, "Валет"),
    TEN(10, "Десятка"),
    NINE(9, "Девятка"),
    EIGHT(8, "Восьмёрка"),
    SEVEN(7, "Семёрка"),
    SIX(6, "Шестёрка"),
    FIVE(5, "Пятёрка"),
    FOUR(4, "Четвёрка"),
    THREE(3, "Тройка"),
    TWO(2, "Двойка");

    private final int value;
    private final String russianName;

    /**
     * Создаёт достоинство карты.
     *
     * @param value очки
     * @param russianName русское название
     */
    Rank(int value, String russianName) {
        this.value = value;
        this.russianName = russianName;
    }

    /**
     * Возвращает очки.
     *
     * @return очки
     */
    public int getValue() {
        return value;
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