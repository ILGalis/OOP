package ru.nsu.fit.oop.ilg.hashtable.iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Итератор для обхода элементов хеш-таблицы.
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class HashTableIterator<K, V> implements Iterator<HashTable.Entry<K, V>> {
    private final HashTable<K, V> table;
    private int expectedModCount;
    private final List<HashTable.Entry<K, V>>[] tableData;

    private int currentBucket = 0;
    private int currentIndex = 0;
    private HashTable.Entry<K, V> currentEntry = null;
    private HashTable.Entry<K, V> nextEntry = null;

    /**
     * Создаёт итератор.
     *
     * @param table           хеш-таблица
     * @param expectedModCount ожидаемое количество модификаций
     * @param tableData       массив бакетов таблицы
     */
    @SuppressWarnings("unchecked")
    public HashTableIterator(HashTable<K, V> table, int expectedModCount,
                             List<HashTable.Entry<K, V>>[] tableData) {
        this.table = table;
        this.expectedModCount = expectedModCount;
        this.tableData = tableData;
        findNext();
    }

    @Override
    public boolean hasNext() {
        return nextEntry != null;
    }

    @Override
    public HashTable.Entry<K, V> next() {
        if (table.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        if (nextEntry == null) {
            throw new NoSuchElementException();
        }
        currentEntry = nextEntry;
        findNext();
        return currentEntry;
    }

    @Override
    public void remove() {
        if (table.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        if (currentEntry == null) {
            throw new IllegalStateException();
        }
        table.remove(currentEntry.getKey());
        expectedModCount = table.getModCount();
        currentEntry = null;
    }

    private void findNext() {
        nextEntry = null;
        while (currentBucket < tableData.length) {
            List<HashTable.Entry<K, V>> bucket = tableData[currentBucket];
            if (currentIndex < bucket.size()) {
                nextEntry = bucket.get(currentIndex++);
                return;
            }
            currentBucket++;
            currentIndex = 0;
        }
    }
}