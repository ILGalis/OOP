package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Проверяет работу класса Participant.
 */
public class ParticipantTest {

    @Test
    void newParticipantShouldHaveEmptyHand() {
        Participant participant = new Participant() {};

        assertNotNull(participant.getHand());
        assertEquals(0, participant.getHand().getCards().size());
    }

    @Test
    void participantShouldBeAbleToTakeCard() {
        Participant participant = new Participant() {};
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        participant.takeCard(card);

        assertEquals(1, participant.getHand().getCards().size());
        assertEquals(card, participant.getHand().getCards().get(0));
    }

    @Test
    void participantShouldReturnHandScore() {
        Participant participant = new Participant() {};

        participant.takeCard(new Card(Suit.HEARTS, Rank.TEN));
        participant.takeCard(new Card(Suit.SPADES, Rank.SEVEN));

        assertEquals(17, participant.getScore());
    }
}