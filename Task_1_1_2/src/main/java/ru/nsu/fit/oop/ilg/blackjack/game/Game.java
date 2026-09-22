package ru.nsu.fit.oop.ilg.blackjack.game;

import java.util.Scanner;
import java.util.function.Consumer;

import ru.nsu.fit.oop.ilg.blackjack.view.ConsoleView;

/**
 * Управляет логикой игры Blackjack.
 * Не знает о способе вывода информации.
 */
public class Game {
    private int playerWins;
    private int dealerWins;

    /**
     * Создаёт новую игру.
     */
    public Game() {
        playerWins = 0;
        dealerWins = 0;
    }

    /**
     * Запускает игру.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        ConsoleView view = new ConsoleView();

        while (true) {
            RoundResult result = playRound(
                    round -> {
                        view.showPlayerHand(round.getPlayer());
                        view.showDealerFirstCard(round.getDealer());
                        view.showMessage("Взять карту? 0 - нет, 1 - да");
                        return scanner.nextLine().equals("1");
                    },
                    round -> view.showDealerHand(round.getDealer())
            );

            view.showMessage(result.getMessage());
            view.showScore(playerWins, dealerWins);

            view.showMessage("");
            view.showMessage("Играть ещё? 1 - да, 0 - нет");

            if (scanner.nextLine().equals("0")) {
                break;
            }
        }

        scanner.close();
    }

    /**
     * Играет один раунд.
     *
     * @param decision  решение игрока о взятии карты
     * @param roundView обработчик завершённого раунда
     * @return результат раунда
     */
    public RoundResult playRound(PlayerDecision decision,
                                 Consumer<Round> roundView) {
        Round round = new Round();
        round.startRound();

        if (round.getPlayer().isBlackjack()) {
            playerWins++;
            roundView.accept(round);
            return RoundResult.PLAYER_WIN;
        }

        while (decision.wantsCard(round)) {
            round.playerTakeCard();

            if (round.getPlayer().isBust()) {
                dealerWins++;
                roundView.accept(round);
                return RoundResult.DEALER_WIN;
            }
        }

        round.dealerTurn();
        roundView.accept(round);

        RoundResult result = round.determineResult();

        if (result == RoundResult.PLAYER_WIN) {
            playerWins++;
        } else if (result == RoundResult.DEALER_WIN) {
            dealerWins++;
        }

        return result;
    }

    /**
     * Возвращает количество побед игрока.
     *
     * @return победы игрока
     */
    public int getPlayerWins() {
        return playerWins;
    }

    /**
     * Возвращает количество побед дилера.
     *
     * @return победы дилера
     */
    public int getDealerWins() {
        return dealerWins;
    }

    /**
     * Функциональный интерфейс для решения игрока.
     */
    @FunctionalInterface
    public interface PlayerDecision {

        /**
         * Хочет ли игрок взять карту.
         *
         * @param round текущий раунд
         * @return {@code true}, если хочет
         */
        boolean wantsCard(Round round);
    }
}