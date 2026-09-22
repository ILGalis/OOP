package ru.nsu.fit.oop.ilg.blackjack.hand;

import ru.nsu.fit.oop.ilg.blackjack.card.Card;
import ru.nsu.fit.oop.ilg.blackjack.card.Rank;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет руку игрока или дилера.
 */
public class Hand {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_REDUCTION = 10;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    /**
     * Создаёт пустую руку.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Добавляет карту в руку.
     *
     * @param card карта
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Вычисляет количество очков в руке.
     *
     * @return очки
     */
    public int getScore() {
        int total = 0;
        int countAce = 0;
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                countAce++;
            }
            total += card.getRank().getValue();
        }
        while (total > BLACKJACK_SCORE && countAce > 0) {
            total -= ACE_REDUCTION;
            countAce--;
        }
        return total;
    }

    /**
     * Проверяет, есть ли Blackjack (две карты с суммой 21).
     *
     * @return {@code true}, если Blackjack
     */
    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT
                && getScore() == BLACKJACK_SCORE;
    }

    /**
     * Проверяет, есть ли перебор.
     *
     * @return {@code true}, если перебор
     */
    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    /**
     * Возвращает список карт руки.
     *
     * @return список карт
     */
    public List<Card> getCards() {
        return cards;
    }
}