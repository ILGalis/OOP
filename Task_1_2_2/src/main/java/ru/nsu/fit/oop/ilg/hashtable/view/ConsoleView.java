package ru.nsu.fit.oop.ilg.hashtable.view;

import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;

/**
 * Отвечает за вывод в консоль.
 */
public class ConsoleView {
    public void printHashTable(HashTable<?, ?> table) {
        System.out.println(table);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}