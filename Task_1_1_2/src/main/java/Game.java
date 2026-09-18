package ru.example.blackjack;

import java.util.Scanner;

/**
 * Управляет игровым процессом Blackjack, включая проведение раундов,
 * взаимодействие с игроком и подсчёт побед игрока и дилера.
 */
public class Game {
    private Round round;
    private Scanner scanner;
    private int playerWins;
    private int dealerWins;

    /**
     * Создаёт новую игру, инициализирует первый раунд,
     * ввод с консоли и начальные значения счёта.
     */
    public Game() {
        round = new Round();
        scanner = new Scanner(System.in);
        playerWins = 0;
        dealerWins = 0;
    }

    /**
     * Запускает основной игровой цикл.
     * В каждом раунде раздаются карты, выполняется ход игрока,
     * затем ход дилера и определяется результат.
     * После завершения раунда игроку предлагается начать новый.
     */
    public void startGame() {
        while (true) {
            round = new Round();
            round.startRound();
            System.out.println("Твои карты: " + round.getPlayer().getHand().getCards());
            System.out.println("Твои очки: " + round.getPlayer().getScore());
            System.out.println("Карта дилера: " + round.getDealer().getHand().getCards().get(0));
            if (round.getPlayer().getHand().isBlackjack()) {
                System.out.println("Blackjack! Ты выиграл!");
                playerWins++;
                showScore();
            }
            else {
                boolean playerContinue = playerTurn();
                if (!playerContinue) {
                    System.out.println("Перебор! Ты проиграл.");
                    dealerWins++;
                    showResult();
                    showScore();
                }
                else {
                    round.dealerTurn();
                    showResult();
                    if (round.getDealer().getHand().isBust()) {
                        System.out.println("Дилер перебрал. Ты выиграл!");
                        playerWins++;
                        showScore();
                    }
                    else if (round.getDealer().getHand().isBlackjack()) {
                        System.out.println("У дилера Blackjack. Ты проиграл.");
                        dealerWins++;
                        showScore();
                    }
                    else if (round.getDealer().getScore() > round.getPlayer().getScore()) {
                        System.out.println("Ты проиграл.");
                        dealerWins++;
                        showScore();
                    }
                    else if (round.getDealer().getScore() == round.getPlayer().getScore()) {
                        System.out.println("Ничья.");
                        showScore();
                    }
                    else {
                        System.out.println("Ты выиграл!");
                        playerWins++;
                        showScore();
                    }
                }
            }
            System.out.println();
            System.out.println("Играть ещё? 1 - да, 0 - нет");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
        }
    }

    /**
     * Выполняет ход игрока.
     * Игрок может брать карты до тех пор, пока не остановится
     * или не получит перебор.
     *
     * @return {@code true}, если игрок остановился без перебора;
     *         {@code false}, если игрок получил перебор
     */
    private boolean playerTurn() {
        while (true) {
            System.out.println();
            System.out.println("Взять карту? 0 - нет, 1 - да");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                round.playerTakeCard();
                System.out.println("Ты взял карту.");
                System.out.println("Твои карты: " + round.getPlayer().getHand().getCards());
                System.out.println("Твои очки: " + round.getPlayer().getScore());
                if (round.getPlayer().getHand().isBust()) {
                    return false;
                }
            }
            else if (input.equals("0")) {
                return true;
            }
            else {
                System.out.println("Введи 0 или 1.");
            }
        }
    }

    /**
     * Выводит на консоль итоговое состояние текущего раунда:
     * карты и очки дилера и игрока.
     */
    private void showResult() {
        System.out.println();
        System.out.println("РЕЗУЛЬТАТ");
        System.out.println("Карты дилера: " + round.getDealer().getHand().getCards());
        System.out.println("Очки дилера: " + round.getDealer().getScore());
        System.out.println("Твои карты: " + round.getPlayer().getHand().getCards());
        System.out.println("Твои очки: " + round.getPlayer().getScore());
    }

    /**
     * Выводит текущий счёт побед игрока и дилера.
     */
    private void showScore() {
        System.out.println("Счёт: Игрок " + playerWins +
                " : " + dealerWins + " Дилер");
    }
}