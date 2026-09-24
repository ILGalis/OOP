package ru.nsu.fit.oop.ilg.blackjack.view;

import ru.nsu.fit.oop.ilg.blackjack.player.Dealer;
import ru.nsu.fit.oop.ilg.blackjack.player.Player;

/**
 * Отвечает за вывод игровой информации в консоль.
 */
public class ConsoleView {

    /**
     * Выводит карты и очки игрока.
     *
     * @param player игрок
     */
    public void showPlayerHand(Player player) {
        System.out.println("Твои карты: " + player.getHand().getCards());
        System.out.println("Твои очки: " + player.getScore());
    }

    /**
     * Выводит первую карту дилера.
     *
     * @param dealer дилер
     */
    public void showDealerFirstCard(Dealer dealer) {
        System.out.println("Карта дилера: " + dealer.getHand().getCards().get(0));
    }

    /**
     * Выводит карты и очки дилера.
     *
     * @param dealer дилер
     */
    public void showDealerHand(Dealer dealer) {
        System.out.println("Карты дилера: " + dealer.getHand().getCards());
        System.out.println("Очки дилера: " + dealer.getScore());
    }

    /**
     * Выводит сообщение.
     *
     * @param message сообщение
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Выводит счёт.
     *
     * @param playerWins победы игрока
     * @param dealerWins победы дилера
     */
    public void showScore(int playerWins, int dealerWins) {
        System.out.println("Счёт: Игрок " + playerWins + " : " + dealerWins + " Дилер");
    }
}