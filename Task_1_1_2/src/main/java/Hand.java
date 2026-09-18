package ru.example.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет руку игрока или дилера и содержит список полученных карт.
 * Также отвечает за подсчёт очков и проверку специальных состояний руки.
 */
public class Hand {
    private List<Card> cards;

    /**
     * Создаёт пустую руку.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Добавляет карту в руку.
     *
     * @param card карта для добавления
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Вычисляет количество очков в руке.
     * Туз первоначально учитывается как 11 очков.
     * Если сумма превышает 21, значение туза уменьшается до 1.
     *
     * @return количество очков в руке
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
        while (total > 21 && countAce > 0) {
            total -= 10;
            countAce--;
        }
        return total;
    }

    /**
     * Проверяет, является ли рука Blackjack.
     * Blackjack определяется как две карты с общей стоимостью 21.
     *
     * @return {@code true}, если рука является Blackjack,
     *         иначе {@code false}
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }

    /**
     * Проверяет, превышает ли сумма очков 21.
     *
     * @return {@code true}, если произошёл перебор,
     *         иначе {@code false}
     */
    public boolean isBust() {
        return getScore() > 21;
    }

    /**
     * Возвращает список карт, находящихся в руке.
     *
     * @return список карт руки
     */
    public List<Card> getCards() {
        return cards;
    }
}