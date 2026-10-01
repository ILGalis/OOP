package ru.nsu.fit.oop.ilg.expression.app;

import java.util.Scanner;

import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.parser.ExpressionParser;

/**
 * Приложение для работы с математическими выражениями.
 * Читает выражение из консоли и выполняет команды.
 */
public class Application {
    private final Scanner scanner;
    private Expression expression;

    /**
     * Создаёт приложение.
     */
    public Application() {
        scanner = new Scanner(System.in);
        expression = null;
    }

    /**
     * Запускает цикл обработки команд.
     */
    public void run() {
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();
        expression = new ExpressionParser(input).parse();

        System.out.println("Выражение:");
        expression.print();

        while (true) {
            System.out.println();
            System.out.println("Команды: print, derivative, eval, exit");
            String command = scanner.nextLine().trim();

            if (command.equals("exit")) {
                break;
            }

            handleCommand(command);
        }

        scanner.close();
    }

    /**
     * Обрабатывает команду.
     *
     * @param command команда
     */
    private void handleCommand(String command) {
        if (command.equals("print")) {
            expression.print();
        } else if (command.equals("derivative")) {
            System.out.println("По какой переменной?");
            String variable = scanner.nextLine().trim();
            Expression derivative = expression.derivative(variable);
            derivative.print();
        } else if (command.equals("eval")) {
            System.out.println("Введите значения (например, x = 10; y = 13):");
            String assignments = scanner.nextLine();
            int result = expression.eval(assignments);
            System.out.println(result);
        } else {
            System.out.println("Неизвестная команда: " + command);
        }
    }
}