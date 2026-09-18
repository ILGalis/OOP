package ru.example.blackjack;

/**
 * Абстрактный базовый класс для участников игры.
 * Хранит руку участника и предоставляет операции
 * для получения карт и текущего количества очков.
 */
public abstract class Body {

    private Hand hand;

    /**
     * Создаёт участника с пустой рукой.
     */
    public Body() {
        hand = new Hand();
    }

    /**
     * Возвращает руку участника.
     *
     * @return рука участника
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Добавляет карту в руку участника.
     *
     * @param card карта, которую нужно добавить
     */
    public void takeCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Возвращает текущее количество очков участника.
     *
     * @return количество очков
     */
    public int getScore() {
        return hand.getScore();
    }
}