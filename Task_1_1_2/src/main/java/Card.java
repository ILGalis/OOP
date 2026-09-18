package ru.example.blackjack;

/**
 * Представляет игральную карту.
 * Каждая карта имеет масть и достоинство.
 */
public class Card {

    private Suit suit;
    private Rank rank;

    /**
     * Создаёт карту с указанной мастью и достоинством.
     *
     * @param suit масть карты
     * @param rank достоинство карты
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Возвращает масть карты.
     *
     * @return масть карты
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Возвращает достоинство карты.
     *
     * @return достоинство карты
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Возвращает строковое представление карты.
     *
     * @return строка с достоинством и мастью карты
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}