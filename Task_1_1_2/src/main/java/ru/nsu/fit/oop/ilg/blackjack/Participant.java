package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Базовый класс для участников игры (игрока и дилера).
 */
public abstract class Participant {
    private final Hand hand;

    /**
     * Создаёт участника с пустой рукой.
     */
    public Participant() {
        hand = new Hand();
    }

    /**
     * Возвращает руку участника.
     *
     * @return рука
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Добавляет карту в руку.
     *
     * @param card карта
     */
    public void takeCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Возвращает текущее количество очков.
     *
     * @return очки
     */
    public int getScore() {
        return hand.getScore();
    }
}