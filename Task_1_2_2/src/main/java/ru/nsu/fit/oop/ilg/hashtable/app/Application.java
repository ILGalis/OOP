package ru.nsu.fit.oop.ilg.hashtable.app;

import java.util.Scanner;

import ru.nsu.fit.oop.ilg.hashtable.core.HashTable;
import ru.nsu.fit.oop.ilg.hashtable.view.ConsoleView;

/**
 * Приложение для работы с хеш-таблицей.
 */
public class Application {
    private final Scanner scanner;
    private final ConsoleView view;
    private final HashTable<String, Double> hashTable;

    public Application() {
        scanner = new Scanner(System.in);
        view = new ConsoleView();
        hashTable = new HashTable<>();
    }

    public void run() {
        view.printMessage("Пример из задания:");
        hashTable.put("one", 1.0);
        hashTable.update("one", 1.0);
        System.out.println(hashTable.get("one"));

        view.printMessage("\nТекущая таблица: " + hashTable);

        boolean running = true;
        while (running) {
            view.printMessage("\nВыберите действие:");
            view.printMessage("1 - put (добавить/обновить)");
            view.printMessage("2 - update (обновить)");
            view.printMessage("3 - get (найти)");
            view.printMessage("4 - remove (удалить)");
            view.printMessage("5 - contains (проверить)");
            view.printMessage("6 - размер таблицы");
            view.printMessage("7 - вывести таблицу");
            view.printMessage("0 - выход");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Ключ: ");
                        String key = scanner.nextLine();
                        System.out.print("Значение: ");
                        Double value = Double.parseDouble(scanner.nextLine());
                        hashTable.put(key, value);
                        view.printMessage("Добавлено.");
                    }
                    case "2" -> {
                        System.out.print("Ключ: ");
                        String key = scanner.nextLine();
                        System.out.print("Новое значение: ");
                        Double value = Double.parseDouble(scanner.nextLine());
                        hashTable.update(key, value);
                        view.printMessage("Обновлено.");
                    }
                    case "3" -> {
                        System.out.print("Ключ: ");
                        String key = scanner.nextLine();
                        view.printMessage("Значение: " + hashTable.get(key));
                    }
                    case "4" -> {
                        System.out.print("Ключ: ");
                        String key = scanner.nextLine();
                        view.printMessage("Удалено: " + hashTable.remove(key));
                    }
                    case "5" -> {
                        System.out.print("Ключ: ");
                        String key = scanner.nextLine();
                        view.printMessage("Содержится: " + hashTable.contains(key));
                    }
                    case "6" -> view.printMessage("Размер: " + hashTable.size());
                    case "7" -> view.printHashTable(hashTable);
                    case "0" -> running = false;
                    default -> view.printMessage("Неверный ввод.");
                }
            } catch (Exception e) {
                view.printMessage("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }
}