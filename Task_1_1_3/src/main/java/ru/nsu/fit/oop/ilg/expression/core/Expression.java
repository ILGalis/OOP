package ru.nsu.fit.oop.ilg.expression.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактное математическое выражение.
 */
public abstract class Expression {

    /**
     * Возвращает производную по заданной переменной.
     * Исходное выражение не меняется.
     *
     * @param variable имя переменной
     * @return новое выражение — производная
     */
    public abstract Expression derivative(String variable);

    /**
     * Вычисляет значение выражения при заданных значениях переменных.
     *
     * @param assignments строка вида "x = 10; y = 13"
     * @return значение выражения
     */
    public int eval(String assignments) {
        return eval(parseAssignments(assignments));
    }

    /**
     * Печатает выражение в консоль.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Вычисляет значение при готовом отображении переменных.
     *
     * @param variables отображение имён в значения
     * @return значение
     */
    protected abstract int eval(Map<String, Integer> variables);

    /**
     * Парсит строку присваиваний в отображение.
     *
     * @param assignments строка вида "x = 10; y = 13"
     * @return отображение имён в значения
     */
    private Map<String, Integer> parseAssignments(String assignments) {
        Map<String, Integer> variables = new HashMap<>();
        String[] pairs = assignments.split(";");
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            if (parts.length != 2) {
                continue;
            }
            String name = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            variables.put(name, value);
        }
        return variables;
    }
}