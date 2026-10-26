package ru.nsu.fit.oop.ilg.hashtable.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import ru.nsu.fit.oop.ilg.hashtable.iterator.HashTableIterator;

/**
 * Параметризованная хеш-таблица с разрешением коллизий методом цепочек.
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class HashTable<K, V> implements Iterable<HashTable.Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Внутренний класс для хранения пары ключ-значение.
     */
    public static class Entry<K, V> {
        private final K key;
        private V value;

        /**
         * Создаёт узел.
         *
         * @param key   ключ
         * @param value значение
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Возвращает ключ.
         *
         * @return ключ
         */
        public K getKey() {
            return key;
        }

        /**
         * Возвращает значение.
         *
         * @return значение
         */
        public V getValue() {
            return value;
        }

        /**
         * Устанавливает значение.
         *
         * @param value значение
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private List<Entry<K, V>>[] table;
    private int size;
    private int modCount;

    /**
     * Создаёт пустую хеш-таблицу.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new List[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        modCount = 0;
    }

    /**
     * Добавляет пару ключ-значение.
     *
     * @param key   ключ
     * @param value значение
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не может быть null");
        }
        if ((float) size / table.length >= LOAD_FACTOR) {
            resize();
        }
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
        modCount++;
    }

    /**
     * Обновляет значение по ключу.
     *
     * @param key   ключ
     * @param value новое значение
     */
    public void update(K key, V value) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        throw new NoSuchElementException("Ключ не найден для обновления: " + key);
    }

    /**
     * Возвращает значение по ключу.
     *
     * @param key ключ
     * @return значение или null
     */
    public V get(K key) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Удаляет пару ключ-значение.
     *
     * @param key ключ
     * @return удалённое значение или null
     */
    public V remove(K key) {
        int index = getIndex(key);
        Iterator<Entry<K, V>> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                iterator.remove();
                size--;
                modCount++;
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Проверяет наличие ключа.
     *
     * @param key ключ
     * @return {@code true}, если ключ есть
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Возвращает количество элементов.
     *
     * @return размер
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает счётчик модификаций.
     *
     * @return счётчик
     */
    public int getModCount() {
        return modCount;
    }

    /**
     * Возвращает массив бакетов.
     *
     * @return массив бакетов
     */
    public List<Entry<K, V>>[] getTableData() {
        return table;
    }

    private int getIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        List<Entry<K, V>>[] oldTable = table;
        table = new List[oldTable.length * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        modCount++;
        for (List<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashTable<?, ?> other = (HashTable<?, ?>) o;
        if (this.size != other.size) {
            return false;
        }

        HashTable rawThis = (HashTable) this;
        HashTable rawOther = (HashTable) other;

        for (Object obj : rawThis) {
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            Object otherValue = rawOther.get(entry.getKey());
            if (!Objects.equals(entry.getValue(), otherValue)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Entry<K, V> entry : this) {
            h += Objects.hashCode(entry.getKey()) ^ Objects.hashCode(entry.getValue());
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Iterator<Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.append("}").toString();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator<>(this, modCount, table);
    }
}