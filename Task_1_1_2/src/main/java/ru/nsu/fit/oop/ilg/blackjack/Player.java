package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Представляет игрока в игре Blackjack.
 */
public class Player extends Participant {

    /**
     * Берёт начальные две карты из колоды.
     *
     * @param deck колода
     */
    public void takeInitialCards(Deck deck) {
        takeCard(deck.takeCard());
        takeCard(deck.takeCard());
    }

    /**
     * Берёт одну карту из колоды.
     *
     * @param deck колода
     */
    public void takeCardFromDeck(Deck deck) {
        takeCard(deck.takeCard());
    }

    /**
     * Проверяет, есть ли у игрока перебор.
     *
     * @return {@code true}, если перебор
     */
    public boolean isBust() {
        return getHand().isBust();
    }

    /**
     * Проверяет, есть ли у игрока Blackjack.
     *
     * @return {@code true}, если Blackjack
     */
    public boolean isBlackjack() {
        return getHand().isBlackjack();
    }
}