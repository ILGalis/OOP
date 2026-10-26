package ru.nsu.fit.oop.ilg.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Тесты итератора.
 */
class HashTableIteratorTest {

    /**
     * Проверяет обход всех элементов.
     */
    @Test
    void shouldIterateOverAllElements() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);

        int count = 0;
        int sum = 0;
        for (HashTable.Entry<String, Integer> entry : table) {
            count++;
            sum += entry.getValue();
        }

        assertEquals(3, count);
        assertEquals(6, sum);
    }

    /**
     * Проверяет ConcurrentModificationException при изменении во время итерации.
     */
    @Test
    void shouldThrowConcurrentModificationException() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);

        Iterator<HashTable.Entry<String, Integer>> it = table.iterator();

        assertThrows(ConcurrentModificationException.class, () -> {
            while (it.hasNext()) {
                it.next();
                table.put("c", 3);
            }
        });
    }

    /**
     * Проверяет, что remove через итератор работает и не ломает таблицу.
     */
    @Test
    void iteratorRemoveShouldRemoveElement() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);

        Iterator<HashTable.Entry<String, Integer>> it = table.iterator();
        while (it.hasNext()) {
            HashTable.Entry<String, Integer> entry = it.next();
            if (entry.getKey().equals("a")) {
                it.remove();
            }
        }

        assertFalse(table.contains("a"));
        assertTrue(table.contains("b"));
        assertEquals(1, table.size());
    }
}