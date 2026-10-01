package ru.nsu.fit.oop.ilg.expression.core;

import java.util.Map;

/**
 * Константа.
 */
public class Number extends Expression {
    private final int value;

    /**
     * Создаёт число.
     *
     * @param value значение
     */
    public Number(int value) {
        this.value = value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    protected int eval(Map<String, Integer> variables) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}