package ru.example.blackjack;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void mainShouldStartGame() {
        String input = "0\n0\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}