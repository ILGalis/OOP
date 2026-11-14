package ru.nsu.fit.oop.ilg.substring.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import ru.nsu.fit.oop.ilg.substring.core.SubstringFinder;

/**
 * Приложение для поиска подстроки в файле.
 */
public class Application {
    private final Scanner scanner;
    private final SubstringFinder finder;

    /**
     * Создаёт приложение.
     */
    public Application() {
        scanner = new Scanner(System.in);
        finder = new SubstringFinder();
    }

    /**
     * Запускает сценарий.
     */
    public void run() {
        System.out.println("Введите имя файла:");
        String filename = scanner.nextLine().trim();
        Path path = Path.of(filename);

        if (!Files.exists(path)) {
            System.out.println("Файл не найден!");
            return;
        }

        System.out.println("Введите подстроку для поиска:");
        String substring = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8))) {

            List<Long> indices = finder.find(reader, substring);
            System.out.println(indices.size());
            System.out.println(indices);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}