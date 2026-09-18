package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoundResultTest {

    @Test
    void shouldContainAllRoundResults() {
        assertEquals(3, Round.RoundResult.values().length);

        assertTrue(java.util.Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.PLAYER_WIN));

        assertTrue(java.util.Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.DEALER_WIN));

        assertTrue(java.util.Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.DRAW));
    }
}