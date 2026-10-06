package ru.nsu.fit.oop.ilg.expression.core;

import java.util.Map;

/**
 * Бинарная операция над двумя выражениями.
 */
public abstract class BinaryOperation extends Expression {
    protected final Expression left;
    protected final Expression right;

    /**
     * Создаёт операцию.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + getSymbol() + right + ")";
    }

    @Override
    protected int eval(Map<String, Integer> variables) {
        return calculate(left.eval(variables), right.eval(variables));
    }

    /**
     * Применяет операцию к двум значениям.
     *
     * @param left  левое значение
     * @param right правое значение
     * @return результат
     */
    protected abstract int calculate(int left, int right);

    /**
     * Возвращает символ операции.
     *
     * @return символ
     */
    protected abstract String getSymbol();
}