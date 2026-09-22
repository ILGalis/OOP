package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.blackjack.game.RoundResult;

/**
 * Тесты для перечисления {@link RoundResult}.
 */
public class RoundResultTest {

    /**
     * Проверяет, что перечисление содержит все ожидаемые результаты раунда.
     */
    @Test
    void shouldContainAllRoundResults() {
        assertEquals(3, RoundResult.values().length);

        assertTrue(Arrays.asList(RoundResult.values())
                .contains(RoundResult.PLAYER_WIN));

        assertTrue(Arrays.asList(RoundResult.values())
                .contains(RoundResult.DEALER_WIN));

        assertTrue(Arrays.asList(RoundResult.values())
                .contains(RoundResult.DRAW));
    }

    /**
     * Проверяет сообщение для каждого результата.
     */
    @Test
    void shouldReturnCorrectMessages() {
        assertEquals("Ты выиграл!", RoundResult.PLAYER_WIN.getMessage());
        assertEquals("Ты проиграл.", RoundResult.DEALER_WIN.getMessage());
        assertEquals("Ничья.", RoundResult.DRAW.getMessage());
    }
}