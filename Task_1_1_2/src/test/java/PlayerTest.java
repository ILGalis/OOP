package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void newPlayerShouldHaveEmptyHand() {
        Player player = new Player();

        assertNotNull(player.getHand());
        assertEquals(0, player.getHand().getCards().size());
    }

    @Test
    void playerShouldBeAbleToTakeCard() {
        Player player = new Player();
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        player.takeCard(card);

        assertEquals(1, player.getHand().getCards().size());
        assertEquals(card, player.getHand().getCards().get(0));
    }

    @Test
    void playerShouldReturnCorrectScore() {
        Player player = new Player();

        player.takeCard(new Card(Suit.HEARTS, Rank.TEN));
        player.takeCard(new Card(Suit.SPADES, Rank.SEVEN));

        assertEquals(17, player.getScore());
    }
}