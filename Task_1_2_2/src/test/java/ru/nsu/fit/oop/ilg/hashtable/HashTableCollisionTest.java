package ru.nsu.fit.oop.ilg.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Тесты коллизий и расширения таблицы.
 */
class HashTableCollisionTest {

    private static class CollisionKey {
        private final String name;

        CollisionKey(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CollisionKey that = (CollisionKey) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return 42;
        }
    }

    /**
     * Проверяет работу при коллизиях.
     */
    @Test
    void shouldHandleCollisions() {
        HashTable<CollisionKey, Integer> table = new HashTable<>();
        CollisionKey k1 = new CollisionKey("a");
        CollisionKey k2 = new CollisionKey("b");
        CollisionKey k3 = new CollisionKey("c");

        table.put(k1, 1);
        table.put(k2, 2);
        table.put(k3, 3);

        assertEquals(1, table.get(k1));
        assertEquals(2, table.get(k2));
        assertEquals(3, table.get(k3));
        assertEquals(3, table.size());
    }

    /**
     * Проверяет, что таблица расширяется при превышении load factor.
     */
    @Test
    void shouldResizeOnLoadFactor() {
        HashTable<Integer, Integer> table = new HashTable<>();

        for (int i = 0; i < 100; i++) {
            table.put(i, i * 10);
        }

        assertEquals(100, table.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i * 10, table.get(i));
        }
    }
}