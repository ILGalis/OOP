package ru.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testAdd() {
        assertEquals(5, Main.add(2, 3));
        assertEquals(0, Main.add(0, 0));
        assertEquals(-1, Main.add(-2, 1));
    }

    @Test
    void testSubtract() {
        assertEquals(2, Main.subtract(5, 3));
        assertEquals(0, Main.subtract(0, 0));
        assertEquals(-3, Main.subtract(-2, 1));
    }
}