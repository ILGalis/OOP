package ru.nsu.fit.oop.ilg.expression.operation;

import ru.nsu.fit.oop.ilg.expression.core.BinaryOperation;
import ru.nsu.fit.oop.ilg.expression.core.Expression;

/**
 * Сложение.
 */
public class Add extends BinaryOperation {

    /**
     * Создаёт сложение.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "+";
    }

    @Override
    protected int calculate(int left, int right) {
        return left + right;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }
}