package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Представляет дилера в игре Blackjack.
 */
public class Dealer extends Participant {
    private static final int DEALER_MIN_SCORE = 17;

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
     * Выполняет ход дилера: берёт карты, пока очки меньше 17.
     *
     * @param deck колода
     */
    public void playTurn(Deck deck) {
        while (getScore() < DEALER_MIN_SCORE) {
            takeCard(deck.takeCard());
        }
    }
}