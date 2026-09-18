package ru.example.blackjack;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для перечисления {@link Round.RoundResult}.
 */
public class RoundResultTest {

    /**
     * Проверяет, что перечисление содержит все ожидаемые результаты раунда.
     */
    @Test
    void shouldContainAllRoundResults() {
        assertEquals(3, Round.RoundResult.values().length);

        assertTrue(Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.PLAYER_WIN));

        assertTrue(Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.DEALER_WIN));

        assertTrue(Arrays.asList(Round.RoundResult.values())
                .contains(Round.RoundResult.DRAW));
    }
}