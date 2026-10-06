package ru.nsu.fit.oop.ilg.expression.app;

import java.util.Scanner;

import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.parser.ExpressionParser;

/**
 * Приложение для работы с математическими выражениями.
 * Спрашивает выражение и действие, выводит результат.
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
     * Запускает сценарий.
     */
    public void run() {
        System.out.println("Введите выражение:");
        expression = new ExpressionParser(scanner.nextLine()).parse();

        System.out.println("Что сделать? (print / derivative / eval):");
        Command command = Command.fromString(scanner.nextLine().trim());

        handleCommand(command);

        scanner.close();
    }

    /**
     * Обрабатывает команду.
     *
     * @param command команда
     */
    private void handleCommand(Command command) {
        switch (command) {
            case PRINT -> expression.print();
            case DERIVATIVE -> handleDerivative();
            case EVAL -> handleEval();
            default -> System.out.println("Неизвестная команда");
        }
    }

    /**
     * Обрабатывает команду дифференцирования.
     */
    private void handleDerivative() {
        System.out.println("По какой переменной?");
        String variable = scanner.nextLine().trim();
        expression.derivative(variable).print();
    }

    /**
     * Обрабатывает команду вычисления.
     */
    private void handleEval() {
        System.out.println("Введите значения (x = 10; y = 13):");
        String assignments = scanner.nextLine();
        System.out.println(expression.eval(assignments));
    }

    /**
     * Команды приложения.
     */
    private enum Command {
        PRINT,
        DERIVATIVE,
        EVAL,
        UNKNOWN;

        /**
         * Находит команду по имени.
         *
         * @param name имя команды
         * @return команда
         */
        private static Command fromString(String name) {
            for (Command command : values()) {
                if (command.name().equalsIgnoreCase(name)) {
                    return command;
                }
            }
            return UNKNOWN;
        }
    }
}