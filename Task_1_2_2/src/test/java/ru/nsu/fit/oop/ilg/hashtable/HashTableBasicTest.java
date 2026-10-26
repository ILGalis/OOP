package ru.nsu.fit.oop.ilg.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals; // <-- ВОТ СЮДА
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Тесты основных операций хеш-таблицы.
 */
class HashTableBasicTest {

    /**
     * Проверяет добавление и получение.
     */
    @Test
    void shouldPutAndGet() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);
        table.put("two", 2);

        assertEquals(1, table.get("one"));
        assertEquals(2, table.get("two"));
        assertEquals(2, table.size());
    }

    /**
     * Проверяет, что put с существующим ключом обновляет значение.
     */
    @Test
    void putShouldOverwriteExistingKey() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);
        table.put("one", 100);

        assertEquals(100, table.get("one"));
        assertEquals(1, table.size());
    }

    /**
     * Проверяет update.
     */
    @Test
    void shouldUpdateValue() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);
        table.update("one", 10);

        assertEquals(10, table.get("one"));
    }

    /**
     * Проверяет, что update несуществующего ключа кидает исключение.
     */
    @Test
    void updateShouldThrowExceptionForMissingKey() {
        HashTable<String, Integer> table = new HashTable<>();

        assertThrows(NoSuchElementException.class, () -> table.update("none", 1));
    }

    /**
     * Проверяет удаление.
     */
    @Test
    void shouldRemove() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);

        Integer removed = table.remove("one");

        assertEquals(1, removed);
        assertNull(table.get("one"));
        assertEquals(0, table.size());
    }

    /**
     * Проверяет удаление несуществующего ключа.
     */
    @Test
    void removeShouldReturnNullForMissingKey() {
        HashTable<String, Integer> table = new HashTable<>();
        assertNull(table.remove("none"));
    }

    /**
     * Проверяет contains.
     */
    @Test
    void shouldCheckContains() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);

        assertTrue(table.contains("one"));
        assertFalse(table.contains("two"));
    }

    /**
     * Проверяет, что ключ null не допускается.
     */
    @Test
    void nullKeyShouldThrowException() {
        HashTable<String, Integer> table = new HashTable<>();
        assertThrows(IllegalArgumentException.class, () -> table.put(null, 1));
    }
    /**
     * Проверяет, что equals возвращает false для разных типов.
     */
    @Test
    void equalsShouldReturnFalseForOtherType() {
        HashTable<String, Integer> table = new HashTable<>();
        assertNotEquals(table, "String");
    }

    /**
     * Проверяет, что equals возвращает false для таблиц разного размера.
     */
    @Test
    void equalsShouldReturnFalseForDifferentSizes() {
        HashTable<String, Integer> t1 = new HashTable<>();
        HashTable<String, Integer> t2 = new HashTable<>();
        t1.put("a", 1);

        assertNotEquals(t1, t2);
    }

    /**
     * Проверяет, что containsKey возвращает false для null значения.
     */
    @Test
    void containsShouldReturnFalseForNullValue() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", null);

        // Хотя ключ есть, get вернет null, значит contains вернет false (особенность реализации)
        assertFalse(table.contains("a"));
    }
}