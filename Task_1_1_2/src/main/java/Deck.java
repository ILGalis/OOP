package ru.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Представляет колоду игральных карт.
 * Колода содержит карты всех мастей и достоинств.
 */
public class Deck {
    private List<Card> cards;

    /**
     * Создаёт новую колоду и заполняет её всеми возможными картами.
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