package ru.example.blackjack;

/**
 * Перечисление возможных достоинств карт в игре Blackjack.
 * Каждому достоинству соответствует определённое количество очков.
 */
public enum Rank {
    ACE(11),
    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    private int value;

    /**
     * Создаёт достоинство карты с указанным количеством очков.
     *
     * @param value количество очков карты
     */
    Rank(int value) {
        this.value = value;
    }

    /**
     * Возвращает количество очков данного достоинства карты.
     *
     * @return количество очков
     */
    public int getValue(){
        return value;
    }
}