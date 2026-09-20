package ru.nsu.fit.oop.ilg.blackjack;

/**
 * Представляет отдельный раунд игры Blackjack.
 */
public class Round {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;

    /**
     * Создаёт новый раунд.
     */
    public Round() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        deck.shuffle();
    }

    /**
     * Начинает раунд: игрок и дилер берут по две карты.
     */
    public void startRound() {
        player.takeInitialCards(deck);
        dealer.takeInitialCards(deck);
    }

    /**
     * Выдаёт игроку дополнительную карту.
     */
    public void playerTakeCard() {
        player.takeCardFromDeck(deck);
    }

    /**
     * Запускает ход дилера.
     */
    public void dealerTurn() {
        dealer.playTurn(deck);
    }

    /**
     * Определяет результат раунда.
     *
     * @return результат
     */
    public RoundResult determineResult() {
        if (player.isBust()) {
            return RoundResult.DEALER_WIN;
        }
        if (dealer.getHand().isBust()) {
            return RoundResult.PLAYER_WIN;
        }
        if (dealer.getHand().isBlackjack()) {
            return RoundResult.DEALER_WIN;
        }
        if (dealer.getScore() > player.getScore()) {
            return RoundResult.DEALER_WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return RoundResult.DRAW;
        }
        return RoundResult.PLAYER_WIN;
    }

    /**
     * Возвращает игрока.
     *
     * @return игрок
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Возвращает дилера.
     *
     * @return дилер
     */
    public Dealer getDealer() {
        return dealer;
    }
}