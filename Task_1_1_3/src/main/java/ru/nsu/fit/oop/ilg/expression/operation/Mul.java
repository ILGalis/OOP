package ru.nsu.fit.oop.ilg.expression.operation;

import ru.nsu.fit.oop.ilg.expression.core.BinaryOperation;
import ru.nsu.fit.oop.ilg.expression.core.Expression;

/**
 * Умножение.
 */
public class Mul extends BinaryOperation {

    /**
     * Создаёт умножение.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "*";
    }

    @Override
    protected int calculate(int left, int right) {
        return left * right;
    }

    @Override
    public Expression derivative(String variable) {
        Expression du = left.derivative(variable);
        Expression dv = right.derivative(variable);
        return new Add(
                new Mul(du, right),
                new Mul(left, dv)
        );
    }
}