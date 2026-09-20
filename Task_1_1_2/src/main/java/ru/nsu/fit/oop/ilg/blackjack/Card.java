package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Представляет игральную карту.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Создаёт карту.
     *
     * @param suit масть
     * @param rank достоинство
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Возвращает масть карты.
     *
     * @return масть
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Возвращает достоинство карты.
     *
     * @return достоинство
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Возвращает строковое представление карты.
     *
     * @return строка с достоинством и мастью
     */
    @Override
    public String toString() {
        return rank.getRussianName() + " " + suit.getRussianName();
    }
}