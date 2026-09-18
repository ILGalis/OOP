package ru.example.blackjack;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Проверяет запуск главного класса приложения.
 */
public class MainTest {

    @Test
    void mainShouldStartGame() {
        String input = "0\n0\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}