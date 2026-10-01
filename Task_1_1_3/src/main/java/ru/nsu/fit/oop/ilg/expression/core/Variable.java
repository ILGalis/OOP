package ru.nsu.fit.oop.ilg.expression.core;

import java.util.Map;

/**
 * Переменная.
 */
public class Variable extends Expression {
    private final String name;

    /**
     * Создаёт переменную.
     *
     * @param name имя переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    protected int eval(Map<String, Integer> variables) {
        if (!variables.containsKey(name)) {
            return 0;
        }
        return variables.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Возвращает имя переменной.
     *
     * @return имя
     */
    public String getName() {
        return name;
    }
}