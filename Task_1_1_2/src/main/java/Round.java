package ru.example.blackjack;

/**
 * Представляет отдельный раунд игры Blackjack.
 * Управляет колодой, игроком и дилером в рамках текущего раунда.
 */
public class Round {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    /**
     * Возможные результаты раунда.
     */
    public enum RoundResult{
        PLAYER_WIN,
        DEALER_WIN,
        DRAW
    }

    /**
     * Создаёт новый раунд, инициализирует колоду,
     * игрока и дилера, а также перемешивает колоду.
     */
    public Round () {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        deck.shuffle();
    }

    /**
     * Начинает раунд и раздаёт по две карты игроку и дилеру.
     */
    public void startRound() {
        player.takeCard(deck.takeCard());
        player.takeCard(deck.takeCard());
        dealer.takeCard(deck.takeCard());
        dealer.takeCard(deck.takeCard());

    }

    /**
     * Выдаёт игроку одну дополнительную карту из колоды.
     */
    public void playerTakeCard() {
        player.takeCard(deck.takeCard());
    }

    /**
     * Возвращает текущего игрока.
     *
     * @return объект игрока
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Возвращает текущего дилера.
     *
     * @return объект дилера
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Запускает ход дилера.
     * Дилер берёт карты согласно правилам игры.
     */
    public void dealerTurn() {
        dealer.playTurn(deck);
    }
}