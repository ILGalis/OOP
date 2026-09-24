package ru.nsu.fit.oop.ilg.blackjack.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Представляет колоду игральных карт.
 */
public class Deck {
    private final List<Card> cards;

    /**
     * Создаёт новую колоду из 52 карт.
     */
    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Перемешивает карты в колоде.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Извлекает последнюю карту из колоды.
     *
     * @return извлечённая карта
     */
    public Card takeCard() {
        return cards.remove(cards.size() - 1);
    }
}