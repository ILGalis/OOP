package ru.nsu.fit.oop.ilg.blackjack;

import java.util.function.Consumer;

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
     * Играет один раунд.
     *
     * @param decision решение игрока о взятии карты
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
        registerResult(result);
        return result;
    }

    /**
     * Обновляет счёт по результату раунда.
     *
     * @param result результат раунда
     */
    private void registerResult(RoundResult result) {
        playerWins += result.getPlayerWinIncrement();
        dealerWins += result.getDealerWinIncrement();
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