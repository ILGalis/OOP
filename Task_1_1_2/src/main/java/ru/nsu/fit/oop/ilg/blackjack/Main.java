package ru.nsu.fit.oop.ilg.blackjack;

import java.util.Scanner;

/**
 * Точка входа. Управляет взаимодействием с пользователем.
 */
public class Main {

    /**
     * Запускает игру.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleView view = new ConsoleView();
        Game game = new Game();

        while (true) {
            RoundResult result = game.playRound(
                    round -> {
                        view.showPlayerHand(round.getPlayer());
                        view.showDealerFirstCard(round.getDealer());
                        view.showMessage("Взять карту? 0 - нет, 1 - да");
                        return scanner.nextLine().equals("1");
                    },
                    round -> view.showDealerHand(round.getDealer())
            );

            view.showMessage(result.getMessage());
            view.showScore(game.getPlayerWins(), game.getDealerWins());

            view.showMessage("");
            view.showMessage("Играть ещё? 1 - да, 0 - нет");
            if (scanner.nextLine().equals("0")) {
                break;
            }
        }
        scanner.close();
    }
}