package ru.example.blackjack;

/**
 * Представляет дилера в игре.
 * Дилер наследует общие свойства и поведение участника игры.
 */
public class Dealer extends Body {

    /**
     * Выполняет ход дилера.
     * Дилер берёт карты до тех пор, пока количество очков не станет
     * не менее 17.
     *
     * @param deck колода, из которой дилер берёт карты
     */
    public void playTurn(Deck deck) {
        while (getScore() < 17) {
            takeCard(deck.takeCard());
        }
    }
}