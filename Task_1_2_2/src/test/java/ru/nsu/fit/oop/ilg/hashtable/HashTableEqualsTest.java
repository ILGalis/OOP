package ru.nsu.fit.oop.ilg.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Тесты equals и toString.
 */
class HashTableEqualsTest {

    /**
     * Проверяет равенство двух одинаковых таблиц.
     */
    @Test
    void equalTablesShouldBeEqual() {
        HashTable<String, Integer> t1 = new HashTable<>();
        t1.put("a", 1);
        t1.put("b", 2);

        HashTable<String, Integer> t2 = new HashTable<>();
        t2.put("b", 2);
        t2.put("a", 1);

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    /**
     * Проверяет неравенство.
     */
    @Test
    void differentTablesShouldNotBeEqual() {
        HashTable<String, Integer> t1 = new HashTable<>();
        t1.put("a", 1);

        HashTable<String, Integer> t2 = new HashTable<>();
        t2.put("a", 2);

        assertNotEquals(t1, t2);
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldContainAllEntries() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);
        table.put("two", 2);

        String s = table.toString();

        assertTrue(s.contains("one=1"));
        assertTrue(s.contains("two=2"));
        assertTrue(s.startsWith("{"));
        assertTrue(s.endsWith("}"));
    }
}