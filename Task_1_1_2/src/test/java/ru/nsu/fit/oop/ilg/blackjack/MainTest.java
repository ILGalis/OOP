package ru.nsu.fit.oop.ilg.blackjack;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

/**
 * Проверяет запуск главного класса приложения.
 */
public class MainTest {

    /**
     * Проверяет, что main запускается без исключений.
     */
    @Test
    void mainShouldStartGame() {
        String input = "0\n0\n";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> Main.main(new String[]{}));
        } finally {
            System.setIn(originalIn);
        }
    }
}