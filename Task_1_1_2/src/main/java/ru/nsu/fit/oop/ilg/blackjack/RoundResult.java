package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Результат раунда.
 * Каждый результат знает, кому начислить победу и что вывести.
 */
public enum RoundResult {
    PLAYER_WIN("Ты выиграл!", 1, 0),
    DEALER_WIN("Ты проиграл.", 0, 1),
    DRAW("Ничья.", 0, 0);

    private final String message;
    private final int playerWinIncrement;
    private final int dealerWinIncrement;

    /**
     * Создаёт результат раунда.
     *
     * @param message             сообщение для игрока
     * @param playerWinIncrement  прибавка к победам игрока
     * @param dealerWinIncrement  прибавка к победам дилера
     */
    RoundResult(String message, int playerWinIncrement, int dealerWinIncrement) {
        this.message = message;
        this.playerWinIncrement = playerWinIncrement;
        this.dealerWinIncrement = dealerWinIncrement;
    }

    /**
     * Возвращает сообщение для игрока.
     *
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Возвращает прибавку к победам игрока.
     *
     * @return прибавка
     */
    public int getPlayerWinIncrement() {
        return playerWinIncrement;
    }

    /**
     * Возвращает прибавку к победам дилера.
     *
     * @return прибавка
     */
    public int getDealerWinIncrement() {
        return dealerWinIncrement;
    }
}